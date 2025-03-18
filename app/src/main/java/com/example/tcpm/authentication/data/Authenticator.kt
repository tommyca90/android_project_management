package com.example.tcpm.authentication.data

interface Authenticator {

    suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean

    suspend fun createUserWithEmailAndPassword(email: String, password: String): AuthUser

    suspend fun currentUser(): AuthUser

}