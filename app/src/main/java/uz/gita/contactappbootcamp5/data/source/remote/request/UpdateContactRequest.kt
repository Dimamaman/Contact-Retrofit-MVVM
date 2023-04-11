package uz.gita.contactappbootcamp5.data.source.remote.request

data class UpdateContactRequest(
    val id: Int,
    val firstName : String,
    val lastName : String,
    val phone: String
)


