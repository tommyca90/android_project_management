package com.example.tcpm.core.data

import com.example.tcpm.authentication.data.AuthFirebase
import com.example.tcpm.authentication.data.AuthProvider
import com.example.tcpm.user.data.UserFirestore
import com.example.tcpm.user.data.UserRepository

object Graph {

    val authProvider by lazy {
        AuthProvider(AuthFirebase())
    }

    val userRepository by lazy {
        UserRepository(UserFirestore())
    }

}