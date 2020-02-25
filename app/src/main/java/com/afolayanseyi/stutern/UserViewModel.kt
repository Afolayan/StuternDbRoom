package com.afolayanseyi.stutern

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afolayanseyi.stutern.data.Result
import com.afolayanseyi.stutern.data.User
import com.afolayanseyi.stutern.data.UserRepository
import com.afolayanseyi.stutern.data.UserRepositoryImpl
import com.afolayanseyi.stutern.db.UserDao
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao) : ViewModel(){
    val usersLiveData = MutableLiveData<Result<List<User>>>()
    private val repository: UserRepository =
        UserRepositoryImpl(userDao)
    init {
        getAllUsers()
    }

    private fun getAllUsers() {
        usersLiveData.postValue(Result.Loading)

        viewModelScope.launch {
            try {
                val users = repository.getUsersAsync()
                usersLiveData.postValue(Result.Success(users))
            }  catch (exception: Exception) {
                usersLiveData.postValue(Result.Error(exception))
            }
        }
    }

    fun insertUser(user: User){
        viewModelScope.launch {
            repository.insert(user)
            getAllUsers()
        }
    }
}