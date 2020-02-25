package com.afolayanseyi.stutern.data

interface UserRepository {
    suspend fun getUsersAsync(): List<User>
    suspend fun insert(user: User)
}