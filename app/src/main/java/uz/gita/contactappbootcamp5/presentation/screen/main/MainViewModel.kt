package uz.gita.contactappbootcamp5.presentation.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import uz.gita.contactappbootcamp5.data.source.remote.request.AddContactRequest
import uz.gita.contactappbootcamp5.data.source.remote.request.UpdateContactRequest
import uz.gita.contactappbootcamp5.data.source.remote.response.ContactDataResponse

interface MainViewModel {
    val getAllContacts: MutableLiveData<List<ContactDataResponse>>
    val progressBar: MutableLiveData<Boolean>

    fun getAllContacts()

    fun addContact(contact: AddContactRequest)

    fun deleteContact(id: Int)

    fun updateContact(contactData: UpdateContactRequest)
}