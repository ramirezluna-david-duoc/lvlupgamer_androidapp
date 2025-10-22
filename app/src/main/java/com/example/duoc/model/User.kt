package com.example.duoc.model

data class User(
    val email: String = "",
    val password: String = ""
)

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)