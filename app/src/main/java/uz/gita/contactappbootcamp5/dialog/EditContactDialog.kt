package uz.gita.contactappbootcamp5.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.contactappbootcamp5.R
import uz.gita.contactappbootcamp5.databinding.DialogAddContactBinding

class EditContactDialog(
    private val context: Context,
    private val oldName: String,
    private val oldLastName: String,
    private val oldPhoneNumber: String
) : AlertDialog(context) {

    private var editContactListener: ((String, String, String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_contact, null, false)
        setContentView(view)

        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        val inputName: AppCompatEditText = view.findViewById(R.id.firstNameEdit)
        val inputLastName: AppCompatEditText = view.findViewById(R.id.lastNameEdit)
        val inputPhoneNumber: AppCompatEditText = view.findViewById(R.id.phoneNumberEdit)


        val buttonClose: CardView = view.findViewById(R.id.close)
        val buttonSave: CardView = view.findViewById(R.id.save)

        inputName.setText(oldName)
        inputLastName.setText(oldLastName)
        inputPhoneNumber.setText(oldPhoneNumber)

        buttonClose.setOnClickListener { dismiss() }

        buttonSave.setOnClickListener {
            editContactListener?.invoke(
                inputName.text.toString(),
                inputLastName.text.toString(),
                inputPhoneNumber.text.toString()
            )
            dismiss()
        }
    }

    fun setEditContactListener(block: ((String,String,String) -> Unit)) {
        editContactListener = block
    }
}
