package com.example.loginexample.repository

import com.example.loginexample.database.UserDao
import com.example.loginexample.model.User


class UserRepository(private val userDao: UserDao) {
    var user: User? = null
    fun isValidUser(username: String?, password: String?): Boolean {
        user = userDao.getUser(username, password)
        return user?.userId == username && user?.password == password
    }

    fun insertUser(username: String, password: String) {
        val user = User(username, password)
        userDao.insert(user)
    }

    fun getUserId(): String? {
        return user?.userId
    }

    fun isUserExist(): Boolean {
        return !userDao.getAllUsers().isNullOrEmpty()
    }

    companion object {
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao): UserRepository {
            if (instance == null) {
                instance = UserRepository(userDao)
            }
            return instance!!
        }
    }
}