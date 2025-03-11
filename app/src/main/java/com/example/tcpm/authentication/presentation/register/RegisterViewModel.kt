package com.example.tcpm.authentication.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {
    private val _username = mutableStateOf("")
    val username: State<String> = _username

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _passwordRepetition = mutableStateOf("")
    val passwordRepetition: State<String> = _passwordRepetition

    fun onUsernameChanged(username: String){
        _username.value = username.trim()
    }

    fun onEmailChanged(email: String){
        _email.value = email.trim()
    }

    fun onPasswordChanged(password: String){
        _password.value = password
    }

    fun onPasswordRepetitionChanged(passwordRepetition: String){
        _passwordRepetition.value = passwordRepetition
    }

}