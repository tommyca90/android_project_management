package com.example.tcpm.authentication.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthFirebase: Authenticator {
    private val _auth = FirebaseAuth.getInstance()

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean{
        return withContext(Dispatchers.IO) {
            val result = _auth.signInWithEmailAndPassword(email, password).await()
            result.user != null
        }
    }

    override suspend fun createUserWithEmailAndPassword(email: String, password: String): AuthUser{
        val firebaseUser = withContext(Dispatchers.IO) {
            _auth.createUserWithEmailAndPassword(email, password).await().user
        }
        return convertToAuthUser(firebaseUser)
    }

    override suspend fun currentUser(): AuthUser {
        return convertToAuthUser(_auth.currentUser)
    }

    private fun convertToAuthUser(firebaseUser: FirebaseUser?): AuthUser {
        val email = firebaseUser?.email ?: ""
        val userId = firebaseUser?.uid ?: ""
        val isAuthenticated = email != "" && userId != ""
        return if(isAuthenticated) AuthUser(isAuthenticated = true, email = email, userId = userId) else AuthUser()
    }
}