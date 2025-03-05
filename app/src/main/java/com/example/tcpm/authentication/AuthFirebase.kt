package com.example.tcpm.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthFirebase {
    private val _auth = FirebaseAuth.getInstance()

    suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean{
        return withContext(Dispatchers.IO) {
            val result = _auth.signInWithEmailAndPassword(email, password).await()
            result.user != null
        }
    }

    suspend fun createUserWithEmailAndPassword(email: String, password: String): FirebaseUser?{
        return withContext(Dispatchers.IO) {
            _auth.createUserWithEmailAndPassword(email, password).await().user
        }
    }
}