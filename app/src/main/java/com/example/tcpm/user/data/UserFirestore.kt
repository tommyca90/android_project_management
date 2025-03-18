package com.example.tcpm.user.data

import com.example.tcpm.persistence.data.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UserFirestore: UserDao {
    private val _firestore = FirebaseFirestore.getInstance()

    override suspend fun addUser(user: User){
        updateUser(user)
    }

    override suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO){
            _firestore.collection(Constants.USERS)
                .document(getCurrentUserId())
                .set(user, SetOptions.merge()).await()
        }
    }

    override suspend fun getUser(): User {
        return withContext(Dispatchers.IO){
            val document = _firestore.collection(Constants.USERS)
                .document(getCurrentUserId()).get().await()
            document.toObject(User::class.java) ?: User()
        }
    }

    override suspend fun deleteUser(){
        withContext(Dispatchers.IO){
            _firestore.collection(Constants.USERS)
                .document(getCurrentUserId()).delete().await()
        }
    }

    private fun getCurrentUserId(): String {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}