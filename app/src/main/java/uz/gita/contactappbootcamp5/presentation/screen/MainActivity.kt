package uz.gita.contactappbootcamp5.presentation.screen

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.gita.contactappbootcamp5.R
import uz.gita.contactappbootcamp5.presentation.adapter.ContactAdapter
import uz.gita.contactappbootcamp5.databinding.ActivityMainBinding
import uz.gita.contactappbootcamp5.dialog.AddContactDialog
import uz.gita.contactappbootcamp5.dialog.EditContactDialog
import uz.gita.contactappbootcamp5.dialog.EventDialog
import uz.gita.contactappbootcamp5.data.source.remote.api.Api
import uz.gita.contactappbootcamp5.data.source.remote.MyClient
import uz.gita.contactappbootcamp5.data.source.remote.request.AddContactRequest
import uz.gita.contactappbootcamp5.data.source.remote.request.UpdateContactRequest
import uz.gita.contactappbootcamp5.data.source.remote.response.ContactDataResponse
import uz.gita.contactappbootcamp5.data.source.remote.response.ErrorData
import uz.gita.contactappbootcamp5.presentation.screen.main.MainViewModel
import uz.gita.contactappbootcamp5.presentation.screen.main.mainViewModelImpl.MainViewModelImpl

class MainActivity : AppCompatActivity() {

    private val adapter = ContactAdapter()
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.refresh.setOnRefreshListener {
            viewModel.getAllContacts()
            binding.refresh.isRefreshing = false
        }

        viewModel.getAllContacts.observe(this) {
            adapter.submitList(it)
        }

        viewModel.progressBar.observe(this) {
            if (it) {
                binding.progress.show()
            } else {
                binding.progress.hide()
            }
        }

        binding.contactList.adapter = adapter
        binding.contactList.layoutManager = LinearLayoutManager(this)

        binding.buttonAdd.setOnClickListener {
            val dialog = AddContactDialog()

            dialog.setAddContactListener { firstname, lastname, phone ->
                val data = AddContactRequest(firstname,lastname,phone)
                viewModel.addContact(data)
            }
            dialog.show(supportFragmentManager, "AddContact")
        }

        adapter.setOpenBottomSheetClick { id, fullName, phoneNumber ->
            val bottomSheet = EventDialog()
            bottomSheet.show(supportFragmentManager, "men")

            bottomSheet.setClickEditButtonListener {
                val wordArray = fullName.split(" ").toTypedArray()
                val editDialog = EditContactDialog(this, wordArray[0], wordArray[1], phoneNumber)
                editDialog.show()
                bottomSheet.dismiss()

                editDialog.setEditContactListener { firstName, lastName, phoneNumber ->
                    val request = UpdateContactRequest(
                        id,
                        firstName = firstName,
                        lastName = lastName,
                        phone = phoneNumber
                    )
                    viewModel.updateContact(request)
                }
                bottomSheet.dismiss()
            }

            bottomSheet.setClickDeleteButtonListener {
                viewModel.deleteContact(id)
                bottomSheet.dismiss()
            }
        }
    }





    /*private fun loadContact() {
        binding.progress.show()
        api.getAllContact().enqueue(object : Callback<List<ContactDataResponse>> {
            override fun onResponse(
                call: Call<List<ContactDataResponse>>,
                response: Response<List<ContactDataResponse>>
            ) {
                binding.progress.hide()
                if (response.isSuccessful) {
                    response.body()?.let { list ->
                        adapter.submitList(list)
                    }
                } else {
                    // server javob bergan
                }
            }

            override fun onFailure(call: Call<List<ContactDataResponse>>, t: Throwable) {
                binding.progress.hide()
                // server bn aloqda muommo
            }
        })
    }

    private fun addContact(firstName: String, lastName: String, phone: String) {
        val request = AddContactRequest(firstName, lastName, phone)
        api.addContact(request).enqueue(object : Callback<ContactDataResponse> {
            override fun onResponse(
                call: Call<ContactDataResponse>,
                response: Response<ContactDataResponse>
            ) {
                if (response.isSuccessful) {
                    loadContact()
                } else {
                    val gson = Gson()
                    response.errorBody()?.let {
                        val error = gson.fromJson(it.string(), ErrorData::class.java)
                        Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ContactDataResponse>, t: Throwable) {
                //
            }
        })
    }*/
}


