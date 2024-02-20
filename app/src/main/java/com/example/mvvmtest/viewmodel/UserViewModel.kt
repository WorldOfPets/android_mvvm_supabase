package com.example.mvvmtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmtest.model.UserModel
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from

class UserViewModel : ViewModel() {
    private var _user = MutableLiveData<UserModel>()
    private var _users = MutableLiveData<List<UserModel>>()
    val user:LiveData<UserModel> = _user
    val users:LiveData<List<UserModel>> = _users
    val supabase = createSupabaseClient(
        supabaseUrl = "https://cjfluxsuthqktabwgssp.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNqZmx1eHN1dGhxa3RhYndnc3NwIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTQyNDkyMzYsImV4cCI6MjAwOTgyNTIzNn0.tVLr2proLKU6tkrQtPsZJJLc0jMfPsyYbEwtkj3Ffu8"
    ) {
        install(Auth)
        install(Postgrest)
    }
    fun setUser(user:UserModel){
        _user.value = user
    }
    suspend fun addUser(user: UserModel){
        _user.value = supabase.from("user").insert(user){
            select()
        }.decodeSingle()
    }
    suspend fun getAllUser(){
        _users.value = supabase.from("user").select().decodeList()
    }
}