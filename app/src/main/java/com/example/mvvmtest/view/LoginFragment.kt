package com.example.mvvmtest.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mvvmtest.R
import com.example.mvvmtest.databinding.FragmentLoginBinding
import com.example.mvvmtest.model.UserModel
import com.example.mvvmtest.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import java.util.*


class LoginFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var bind: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentLoginBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        userViewModel.user.observe(viewLifecycleOwner){
            bind.userTextView.text = "${it.first_name} ${it.last_name}"
            bind.userEmailTextView.text = "${it.email}"
            bind.userBirthDayTextView.text = "${it.birthDay}"
        }
        bind.userAddBtn.setOnClickListener {
            var user = UserModel()
            user.first_name = bind.userFirstNameEditText.text.toString()
            user.last_name = bind.userLastNameEditText.text.toString()
            user.email = bind.userEmailEditText.text.toString()
            user.password = bind.userPasswordEditText.text.toString()
            val calendar = Calendar.getInstance()
            calendar.set(
                bind.birthDayDatePicker.year,
                bind.birthDayDatePicker.month,
                bind.birthDayDatePicker.dayOfMonth
            )
            user.birthDay = calendar.time.toString()
            lifecycleScope.launch {
                userViewModel.addUser(user)
            }
        }
        bind.showUsers.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_userModelFragment)
        }


        return bind.root
    }
}