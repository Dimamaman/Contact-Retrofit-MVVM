package uz.gita.contactappbootcamp5.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.contactappbootcamp5.databinding.ItemContactBinding
import uz.gita.contactappbootcamp5.data.source.remote.response.ContactDataResponse

class ContactAdapter : ListAdapter<ContactDataResponse, ContactAdapter.ContactViewHolder>(ContactDiffUtil) {

    private var openBottomSheetClick: ((Int, String, String) -> Unit)? = null
    fun setOpenBottomSheetClick(l: (Int, String, String) -> Unit) {
        openBottomSheetClick = l
    }

    object ContactDiffUtil : DiffUtil.ItemCallback<ContactDataResponse>() {
        override fun areItemsTheSame(oldItem: ContactDataResponse, newItem: ContactDataResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ContactDataResponse, newItem: ContactDataResponse): Boolean {
            return oldItem.firstName == newItem.firstName &&
                    oldItem.lastName == newItem.lastName
        }
    }

    inner class ContactViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.buttonMore.setOnClickListener {
                openBottomSheetClick?.invoke(
                    getItem(absoluteAdapterPosition).id,
                    binding.textName.text.toString(),
                    binding.textNumber.text.toString()
                )
            }
        }

        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                binding.textName.text = "$lastName $firstName"
                binding.textNumber.text = phone
            }
        }
    }
/*
    fun showPopup(view: View) {
        val popup = PopupMenu(view.context, view)
        popup.inflate(R.menu.edit_contact)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.more -> {

                    true
                }
                else -> false
            }
        }
        popup.show()
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContactViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind()
    }


}

