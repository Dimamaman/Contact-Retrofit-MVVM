package uz.gita.contactappbootcamp5.domain.impl

import retrofit2.Call
import retrofit2.Callback
import uz.gita.contactappbootcamp5.data.source.remote.MyClient
import uz.gita.contactappbootcamp5.data.source.remote.api.Api
import uz.gita.contactappbootcamp5.data.source.remote.request.AddContactRequest
import uz.gita.contactappbootcamp5.data.source.remote.request.UpdateContactRequest
import uz.gita.contactappbootcamp5.data.source.remote.response.ContactDataResponse
import uz.gita.contactappbootcamp5.domain.AppRepository

class AppRepositoryImpl : AppRepository {

    private val api = MyClient.retrofit.create(Api::class.java)
    override fun getAllContacts(callBack: Callback<List<ContactDataResponse>>) {
        api.getAllContact().enqueue(callBack)
    }

    override fun addContact(contact: AddContactRequest, callBack: Callback<ContactDataResponse>) {
        api.addContact(contact).enqueue(callBack)
    }

    override fun deleteContact(id: Int, callback: Callback<Unit>) {
        api.delete(id).enqueue(callback)
    }

    override fun updateContact(
        contactData: UpdateContactRequest,
        callBack: Callback<ContactDataResponse>
    ) {
        api.updateContact(contactData).enqueue(callBack)
    }

}