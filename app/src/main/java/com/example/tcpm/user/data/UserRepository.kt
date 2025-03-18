package com.example.tcpm.user.data

import com.example.tcpm.authentication.data.AuthenticatedUser

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(
        authenticatedUser: AuthenticatedUser,
        username: String = "",
        imageUrl: String = ""
    ) {
        userDao.addUser(
            User(
                userId = authenticatedUser.userId,
                email = authenticatedUser.email,
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