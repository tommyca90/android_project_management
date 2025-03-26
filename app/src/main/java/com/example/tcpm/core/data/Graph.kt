package com.example.tcpm.core.data

import com.example.tcpm.authentication.data.AuthFirebase
import com.example.tcpm.authentication.data.AuthProvider
import com.example.tcpm.project.data.ProjectFirestore
import com.example.tcpm.project.data.ProjectRepository
import com.example.tcpm.user.data.UserFirestore
import com.example.tcpm.user.data.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

object Graph {

    init {
        val firestore = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setHost("10.0.2.2:8080")
            .setSslEnabled(false)
            .build()
        firestore.firestoreSettings = settings

        Firebase.auth.useEmulator("10.0.2.2", 9099)
    }

    val authProvider by lazy {
        AuthProvider(AuthFirebase())
    }

    val userRepository by lazy {
        UserRepository(UserFirestore())
    }

    val projectRepository by lazy {
        ProjectRepository(ProjectFirestore())
    }

}