package com.example.tcpm.user.data

import com.example.tcpm.authentication.data.AuthUser

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(
        authUser: AuthUser,
        username: String = "",
        imageUrl: String = ""
    ) {
        userDao.addUser(
            User(
                userId = authUser.userId,
                email = authUser.email,
                username = username,
                imageUrl = imageUrl
            )
        )
    }

    suspend fun getUser(): User {
        return userDao.getUser()
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser() {
        userDao.deleteUser()
    }
}