package uz.gita.contactappbootcamp5.data.source.remote.request

data class AddContactRequest(
    val firstName: String,
    val lastName: String,
    val phone: String
)