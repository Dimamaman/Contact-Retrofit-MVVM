package uz.gita.contactappbootcamp5.presentation.screen.main.mainViewModelImpl

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.gita.contactappbootcamp5.app.App
import uz.gita.contactappbootcamp5.data.source.remote.request.AddContactRequest
import uz.gita.contactappbootcamp5.data.source.remote.request.UpdateContactRequest
import uz.gita.contactappbootcamp5.data.source.remote.response.ContactDataResponse
import uz.gita.contactappbootcamp5.data.source.remote.response.ErrorData
import uz.gita.contactappbootcamp5.domain.AppRepository
import uz.gita.contactappbootcamp5.domain.impl.AppRepositoryImpl
import uz.gita.contactappbootcamp5.presentation.screen.main.MainViewModel

class MainViewModelImpl : ViewModel(), MainViewModel {
    private val repository: AppRepository = AppRepositoryImpl()

    override val getAllContacts: MutableLiveData<List<ContactDataResponse>> = MutableLiveData()

    override val progressBar: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getAllContacts()
    }

    override fun getAllContacts() {
        progressBar.value = true
        repository.getAllContacts(object : Callback<List<ContactDataResponse>> {
            override fun onResponse(
                call: Call<List<ContactDataResponse>>,
                response: Response<List<ContactDataResponse>>
            ) {
                if (response.isSuccessful) {
                    getAllContacts.value = response.body()
                    progressBar.value = false
                } else {
                    val gson = Gson()
                    response.errorBody()?.let {
                        val error = gson.fromJson(it.string(), ErrorData::class.java)
                        Toast.makeText(App.instance, error.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<ContactDataResponse>>, t: Throwable) {
                Toast.makeText(App.instance, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun addContact(contact: AddContactRequest) {
        repository.addContact(contact,object : Callback<ContactDataResponse> {
            override fun onResponse(
                call: Call<ContactDataResponse>,
                response: Response<ContactDataResponse>
            ) {
                if (response.isSuccessful) {
                    getAllContacts()
                } else {
                    val gson = Gson()
                    response.errorBody()?.let {
                        val error = gson.fromJson(it.string(), ErrorData::class.java)
                        Toast.makeText(App.instance, error.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ContactDataResponse>, t: Throwable) {
                Toast.makeText(App.instance, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun deleteContact(id: Int) {
        repository.deleteContact(id,object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    getAllContacts()
                } else {
                    val gson = Gson()
                    response.errorBody()?.let {
                        val error = gson.fromJson(it.string(), ErrorData::class.java)
                        Toast.makeText(App.instance, error.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(App.instance, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun updateContact(contactData: UpdateContactRequest) {
        repository.updateContact(contactData, object : Callback<ContactDataResponse> {
            override fun onResponse(
                call: Call<ContactDataResponse>,
                response: Response<ContactDataResponse>
            ) {
                if (response.isSuccessful) {
                    getAllContacts()
                } else {
                    val gson = Gson()
                    response.errorBody()?.let {
                        val error = gson.fromJson(it.string(), ErrorData::class.java)
                        Toast.makeText(App.instance, error.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ContactDataResponse>, t: Throwable) {
                Toast.makeText(App.instance, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}