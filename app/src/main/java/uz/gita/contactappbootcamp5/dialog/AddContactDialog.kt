package uz.gita.contactappbootcamp5.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.contactappbootcamp5.R
import uz.gita.contactappbootcamp5.databinding.DialogAddContactBinding

class AddContactDialog : DialogFragment(R.layout.dialog_add_contact) {
    private var addContactListener: ((String, String, String) -> Unit)? = null
    private val binding by viewBinding(DialogAddContactBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.close.setOnClickListener { dismiss() }
        binding.save.setOnClickListener {
            addContactListener?.invoke(
                binding.firstName.text.toString(),
                binding.lastName.text.toString(),
                binding.phoneNumber.text.toString()
            )
            dismiss()
        }
    }

    fun setAddContactListener(block: (String, String, String) -> Unit) {
        addContactListener = block
    }
}


