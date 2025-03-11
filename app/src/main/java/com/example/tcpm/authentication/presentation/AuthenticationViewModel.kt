package com.example.tcpm.authentication.presentation

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcpm.authentication.AuthFirebase
import com.example.tcpm.authentication.data.UserData
import com.example.tcpm.persistence.DBFirestore
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthenticationViewModel() : ViewModel() {
    private val _auth = Firebase.auth
    private val _userData = MutableStateFlow(UserData())
    val userData: StateFlow<UserData> = _userData.asStateFlow()

    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome: StateFlow<Boolean> = _navigateToHome.asStateFlow()

    private val _navigateToLoginRegister = MutableStateFlow(false)
    val navigateToLoginRegister: StateFlow<Boolean> = _navigateToLoginRegister.asStateFlow()

    private val _isRegistrationInProgress = mutableStateOf(false)
    val isRegistrationInProgress: State<Boolean> = _isRegistrationInProgress

    private val _isLogInInProgress = mutableStateOf(false)
    val isLogInInProgress: State<Boolean> = _isLogInInProgress

    private val _errorUsername = mutableStateOf("")
    val errorUsername: State<String> = _errorUsername
    private val _errorEmail = mutableStateOf("")
    val errorEmail: State<String> = _errorEmail
    private val _errorPassword = mutableStateOf("")
    val errorPassword: State<String> = _errorPassword
    private val _errorPasswordRepetition = mutableStateOf("")
    val errorPasswordRepetition = _errorPasswordRepetition
    private val _errorLogIn = mutableStateOf("")
    val errorLogIn = _errorLogIn
    private val _errorRegistration = mutableStateOf("")
    val errorRegistration = _errorRegistration

    fun isLoggedIn(): Boolean {
        val isLoggedIn = _auth.currentUser != null
        if(isLoggedIn){
            viewModelScope.launch {
                _userData.value = DBFirestore().getUserData()
            }
        }
        return isLoggedIn
    }

    fun logInUser(email: String, password: String) {
        if (!isLogInInputValid(email = email, password = password)) {
            return
        }
        _isLogInInProgress.value = true
        viewModelScope.launch(Dispatchers.Main) {
            try {
                AuthFirebase().signInWithEmailAndPassword(email = email, password = password)
                _userData.value = DBFirestore().getUserData()
                _navigateToHome.value = true
            } catch (e: Exception) {
                _errorLogIn.value = e.message ?: ""
            } finally {
                _isLogInInProgress.value = false
            }
        }
    }

    fun logOutUser() {
        _auth.signOut()
        _userData.value = UserData()
        _navigateToLoginRegister.value = true
    }

    fun registerUser(username: String, email: String, password: String, passwordRepetition: String) {
        if (!isRegistrationInputValid(
                username = username,
                email = email,
                password = password,
                passwordRepetition = passwordRepetition
            )
        ) {
            return
        }

        _isRegistrationInProgress.value = true
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val user = AuthFirebase().createUserWithEmailAndPassword(email, password)
                    ?: throw Exception("unable to create user")
                val userData = UserData(
                    email = user.email ?: "",
                    username = username,
                    userId = user.uid
                )
                DBFirestore().updateUserData(userData = userData)
                _navigateToHome.value = true
            } catch (e: Exception) {
                _errorRegistration.value = e.message ?: ""
            } finally {
                _isRegistrationInProgress.value = false
            }
        }
    }

    fun resetErrors() {
        _errorUsername.value = ""
        _errorEmail.value = ""
        _errorPassword.value = ""
        _errorPasswordRepetition.value = ""
        _errorLogIn.value = ""
        _errorRegistration.value = ""
    }

    private fun isLogInInputValid(email: String, password: String): Boolean {
        resetErrors()
        return isEmailValid(email) && isPasswordValid(password)
    }

    private fun isRegistrationInputValid(
        username: String,
        email: String,
        password: String,
        passwordRepetition: String
    ): Boolean {
        resetErrors()
        return isUsernameValid(username) && isEmailValid(email) && isPasswordValid(password) && isPasswordRepetitionValid(
            password,
            passwordRepetition
        )
    }

    private fun isUsernameValid(username: String): Boolean {
        val isValid = username.length < 33
        if (!isValid) {
            _errorUsername.value = "max 32 characters"
        }
        return isValid
    }

    private fun isEmailValid(email: String): Boolean {
        val isValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (!isValid) {
            _errorEmail.value = "invalid email"
        }
        return isValid
    }

    private fun isPasswordValid(password: String): Boolean {
        val isValid = password.length > 7
        if (!isValid) {
            _errorPassword.value = "invalid password"
        }
        return isValid
    }

    private fun isPasswordRepetitionValid(password: String, passwordRepetition: String): Boolean {
        val isValid = password == passwordRepetition
        if (!isValid) {
            _errorPasswordRepetition.value = "passwords don't match"
        }
        return isValid
    }
}