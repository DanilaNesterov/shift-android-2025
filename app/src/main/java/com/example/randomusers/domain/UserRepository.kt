package com.example.randomusers.domain

import com.example.randomusers.data.local.UserEntity
import com.example.randomusers.data.remote.UserResponseDto
import kotlinx.coroutines.flow.Flow

// Интерфейс репозитория для работы с данными пользователей
interface UserRepository {

    // Получение случайных пользователей
    suspend fun getRandomUsers(count: Int): UserResponseDto

    // Добавление списка пользователей в базу данных
    suspend fun addAllUser(users: List<UserEntity>)

    // Получение сохранённых пользователей из базы данных
    fun getSavedUsers(): Flow<List<UserEntity>>

    // Удаление всех пользователей из базы данных
    suspend fun deleteAllUsers()
}