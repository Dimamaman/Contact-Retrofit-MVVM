package uz.gita.contactappbootcamp5.data.source.remote.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query
import uz.gita.contactappbootcamp5.data.source.remote.request.AddContactRequest
import uz.gita.contactappbootcamp5.data.source.remote.request.UpdateContactRequest
import uz.gita.contactappbootcamp5.data.source.remote.response.ContactDataResponse

interface Api {

    @GET("/api/v1/contact")
    fun getAllContact() : Call<List<ContactDataResponse>>

    @POST("/api/v1/contact")
    fun addContact(@Body data : AddContactRequest) : Call<ContactDataResponse>

    @PUT("/api/v1/contact")
    fun updateContact(@Body data : UpdateContactRequest) : Call<ContactDataResponse>

    @DELETE("/api/v1/contact")
    fun delete(@Query("id") id : Int) : Call<Unit>

}