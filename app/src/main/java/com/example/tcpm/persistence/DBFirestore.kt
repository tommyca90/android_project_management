package com.example.tcpm.persistence

import com.example.tcpm.authentication.data.UserData
import com.example.tcpm.persistence.data.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DBFirestore {

    private val _firestore = FirebaseFirestore.getInstance()

    suspend fun updateUserData(userData: UserData) {
        withContext(Dispatchers.IO){
            _firestore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .set(userData, SetOptions.merge()).await()
        }
    }

    suspend fun getUserData(): UserData {
        return withContext(Dispatchers.IO){
            val document = _firestore.collection(Constants.USERS)
                .document(getCurrentUserId()).get().await()
            document.toObject(UserData::class.java) ?: UserData()
        }
    }

    private fun getCurrentUserId(): String {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}