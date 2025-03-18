package com.example.tcpm.user.data

interface UserDao {

    suspend fun addUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun getUser(): User

    suspend fun deleteUser()

}