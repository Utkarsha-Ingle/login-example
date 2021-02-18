package com.example.loginexample.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User
    (@PrimaryKey
     var userId: String,
     var password: String)