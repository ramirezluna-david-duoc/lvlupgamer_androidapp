package com.example.duoc.viewmodel

import androidx.lifecycle.ViewModel
import com.example.duoc.model.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun onEmailChange(email: String) {
        _loginState.update { it.copy(email = email, errorMessage = null) }
    }

    fun onPasswordChange(password: String) {
        _loginState.update { it.copy(password = password, errorMessage = null) }
    }

    fun togglePasswordVisibility() {
        _loginState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun onLoginClick(onSuccess: () -> Unit) {
        val currentState = _loginState.value

        if (currentState.email.isEmpty() || currentState.password.isEmpty()) {
            _loginState.update { it.copy(errorMessage = "Por favor completa todos los campos") }
            return
        }

        _loginState.update { it.copy(isLoading = true) }

        // Simulación de login (aquí iría la lógica real)
        // Por ahora solo validamos que los campos no estén vacíos
        _loginState.update { it.copy(isLoading = false) }
        onSuccess()
    }

    fun clearError() {
        _loginState.update { it.copy(errorMessage = null) }
    }
}

