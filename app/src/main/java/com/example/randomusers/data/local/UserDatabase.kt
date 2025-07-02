package com.example.randomusers.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

// Абстрактный класс базы данных
@Database(
    entities = [UserEntity::class],
    version = 3,
    exportSchema = false
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}