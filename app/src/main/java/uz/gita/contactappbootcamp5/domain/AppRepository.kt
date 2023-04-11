package uz.gita.contactappbootcamp5.domain

import retrofit2.Call
import retrofit2.Callback
import uz.gita.contactappbootcamp5.data.source.remote.request.AddContactRequest
import uz.gita.contactappbootcamp5.data.source.remote.request.UpdateContactRequest
import uz.gita.contactappbootcamp5.data.source.remote.response.ContactDataResponse

interface AppRepository {
    fun getAllContacts(callBack: Callback<List<ContactDataResponse>>)

    fun addContact(contact: AddContactRequest,callBack: Callback<ContactDataResponse>)

    fun deleteContact(id:Int,callBack: Callback<Unit>)

    fun updateContact(contactData: UpdateContactRequest,callBack: Callback<ContactDataResponse>)
}