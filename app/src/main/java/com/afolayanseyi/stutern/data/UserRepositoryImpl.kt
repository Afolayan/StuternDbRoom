package com.afolayanseyi.stutern.data

import com.afolayanseyi.stutern.db.UserDao

class UserRepositoryImpl(private val userDao: UserDao) :
    UserRepository {
    override suspend fun getUsersAsync(): List<User> {
        return userDao.getAllAsync()
    }

    override suspend fun insert(user: User) {
        userDao.insertAll(user)
    }
}