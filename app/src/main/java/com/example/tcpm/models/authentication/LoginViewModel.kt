package com.example.tcpm.models.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    fun onEmailChanged(email: String){
        _email.value = email.trim()
    }

    fun onPasswordChanged(password: String){
        _password.value = password.trim()
    }
}
