package com.example.loginexample.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.loginexample.model.User

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE userId = :username AND password = :password")
    fun getUser(username: String?,password: String?): User?

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User?>?
}