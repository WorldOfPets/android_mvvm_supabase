package com.example.mvvmtest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmtest.databinding.FragmentItemListBinding
import com.example.mvvmtest.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import kotlin.properties.ReadWriteProperty

/**
 * A fragment representing a list of Items.
 */
class UserModelFragment : Fragment() {

    private lateinit var bind:FragmentItemListBinding
    private lateinit var userViewModel:UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentItemListBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        val manager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            userViewModel.getAllUser()
        }

        userViewModel.users.observe(viewLifecycleOwner){
            val adapter = MyUserModelRecyclerViewAdapter(it, userViewModel)
            bind.list.layoutManager = manager
            bind.list.adapter = adapter
        }


        return bind.root
    }

}


