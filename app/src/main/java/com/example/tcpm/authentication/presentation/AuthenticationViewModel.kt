package com.example.tcpm.authentication.presentation

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcpm.authentication.data.AuthUser
import com.example.tcpm.core.data.Graph
import com.example.tcpm.user.data.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthenticationViewModel() : ViewModel() {
    private val _auth = Firebase.auth
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

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

    private val userRepository = Graph.userRepository
    private val authProvider = Graph.authProvider

    private val _authUser = MutableStateFlow(AuthUser())
    val authUser: StateFlow<AuthUser> = _authUser.asStateFlow()

    private val _drawerNavigationIndex = MutableStateFlow(0)
    val drawerNavigationIndex: StateFlow<Int> = _drawerNavigationIndex.asStateFlow()

    init {
        viewModelScope.launch {
            updateUserLogic()
        }
    }

    private suspend fun updateUserLogic(){
        _authUser.value = authProvider.currentUser()
        if (_authUser.value.isAuthenticated) {
            _user.value = userRepository.getUser()
        }else{
            _user.value = User()
        }
    }

    fun logInUser(email: String, password: String) {
        if (!isLogInInputValid(email = email, password = password)) {
            return
        }
        _isLogInInProgress.value = true
        viewModelScope.launch(Dispatchers.Main) {
            try {
                authProvider.signInWithEmailAndPassword(email = email, password = password)
                updateUserLogic()
            } catch (e: Exception) {
                _errorLogIn.value = e.message ?: ""
            } finally {
                _isLogInInProgress.value = false
            }
        }
    }

    fun logOutUser() {
        _auth.signOut()
        viewModelScope.launch(Dispatchers.Main) {
            updateUserLogic()
            _drawerNavigationIndex.value = 0
        }
    }

    fun registerUser(
        username: String,
        email: String,
        password: String,
        passwordRepetition: String
    ) {
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
                val authUser = authProvider.createUserWithEmailAndPassword(email, password)
                userRepository.addUser(authUser = authUser, username = username)
                updateUserLogic()
            } catch (e: Exception) {
                _errorRegistration.value = "unable to create user; ${e.message}"
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

    fun onChangeDrawerNavigationIndex(index: Int){
        _drawerNavigationIndex.value = index
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