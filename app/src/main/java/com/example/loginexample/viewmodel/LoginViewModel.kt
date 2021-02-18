package com.example.loginexample.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginexample.database.UserDatabase
import com.example.loginexample.repository.UserRepository

class LoginViewModel(context: Context) : ViewModel() {
    var userId: MutableLiveData<String>? = null
    var password: MutableLiveData<String>? = null
    private val userRepository: UserRepository
    var isUsersAvailable = ObservableField<Boolean>(true)

    init {
        userRepository = UserRepository.getInstance(UserDatabase.getAppDatabase(context).userDao())
        checkUserAvailibility()
        userId = MutableLiveData()
        password = MutableLiveData()
    }

    private fun checkUserAvailibility() {
        isUsersAvailable.set(userRepository.isUserExist())
    }

    fun createUser(username: String, password: String) {
        userRepository.insertUser(username, password)
        checkUserAvailibility()
    }

    fun checkValidLogin(): Boolean {
        return userRepository.isValidUser(userId?.value, password?.value)
    }

    fun getUser(): String? {
        return userRepository.getUserId()
    }

    class Factory constructor(ctxt: Context) : ViewModelProvider.Factory {
        private val ctxt: Context

        init {
            this.ctxt = ctxt.applicationContext
        }

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(ctxt) as T
        }

    }
}