package com.example.tcpm.models.authentication

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.tcpm.Screen
import com.example.tcpm.data.UserData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import java.util.regex.Matcher
import java.util.regex.Pattern


class AuthenticationViewModel(val navController: NavController) : ViewModel() {
    private val _auth = Firebase.auth
    private val _userData = mutableStateOf(UserData())
    val userData: State<UserData> = _userData

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
        return _auth.currentUser != null
    }

    fun logInUser(email: String, password: String) {
        if (!isLogInInputValid(email = email, password = password)) {
            return
        }
        _isLogInInProgress.value = true
        _auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val tmpUser = task.result.user
                    _userData.value = UserData(
                        email = tmpUser?.email ?: "",
                        username = "TODO",
                        userId = tmpUser?.uid ?: ""
                    )
                    navController.navigate(Screen.HomeScreen.route)
                } else {
                    _errorLogIn.value = task.exception?.message ?: ""
                }
                _isLogInInProgress.value = false
            }
    }

    fun logOutUser() {
        _auth.signOut()
        _userData.value = UserData()
        navController.navigate(Screen.LoginRegisterScreen.route)
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
        _auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val tmpUser = task.result.user
                _userData.value = UserData(
                    email = tmpUser?.email ?: "",
                    username = username,
                    userId = tmpUser?.uid ?: ""
                )
                navController.navigate(Screen.HomeScreen.route)
            } else {
                _errorRegistration.value = task.exception?.message ?: ""
            }
            _isRegistrationInProgress.value = false
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
        val pattern: Pattern
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(passwordPattern)
        val matcher: Matcher = pattern.matcher(password)

        val isValid = password.length > 7 && matcher.matches()
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