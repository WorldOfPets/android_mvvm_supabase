package com.example.mvvmtest.model

@kotlinx.serialization.Serializable
data class UserModel(
    var id: Int = 0,
    var first_name: String = "",
    var last_name: String = "",
    var email: String = "",
    var birthDay: String? = "",
    var password: String = ""
)
