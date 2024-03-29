package com.example.mvvmtest.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.mvvmtest.databinding.FragmentItemBinding
import com.example.mvvmtest.model.UserModel
import com.example.mvvmtest.viewmodel.UserViewModel


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyUserModelRecyclerViewAdapter(
    private val values: List<UserModel>,
    private val userViewModel: UserViewModel
)
    : RecyclerView.Adapter<MyUserModelRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.email
        holder.contentView.setOnClickListener {
            userViewModel.setUser(item)
            Navigation.findNavController(holder.contentView).popBackStack()
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

    }

}