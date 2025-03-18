package com.example.tcpm.authentication.data

class AuthProvider(private val authenticator: Authenticator) {

    suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean {
        return authenticator.signInWithEmailAndPassword(email = email, password = password)
    }

    suspend fun createUserWithEmailAndPassword(email: String, password: String): AuthUser {
        return authenticator.createUserWithEmailAndPassword(email = email, password = password)
    }

    suspend fun currentUser(): AuthUser {
        return authenticator.currentUser()
    }

}