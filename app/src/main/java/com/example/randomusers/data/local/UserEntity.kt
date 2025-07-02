package com.example.randomusers.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


// Сущность пользователей в базе данных
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val fullName: String,
    val gender: String,
    val street: String,
    val city: String,
    val country: String,
    val email: String,
    val phone: String,
    val photoUrl: String,
    val nationality: String,
    val postcode: String,
    val dob: String,
    val registered: String
)