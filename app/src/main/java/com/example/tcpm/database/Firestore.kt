package com.example.tcpm.database

import com.example.tcpm.data.UserData
import com.example.tcpm.utils.Constants
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class Firestore {

    private val _firestore = FirebaseFirestore.getInstance()

    fun registerUser(userData: UserData): Task<Void> {
        return _firestore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .set(userData, SetOptions.merge())
    }

    private fun getCurrentUserId(): String {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}