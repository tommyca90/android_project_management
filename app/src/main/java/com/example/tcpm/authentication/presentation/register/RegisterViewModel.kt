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

    fun onChangeUsername(username: String){
        _username.value = username.trim()
    }

    fun onChangeEmail(email: String){
        _email.value = email.trim()
    }

    fun onChangePassword(password: String){
        _password.value = password
    }

    fun onChangePasswordRepetition(passwordRepetition: String){
        _passwordRepetition.value = passwordRepetition
    }

}