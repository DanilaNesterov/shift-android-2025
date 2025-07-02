package com.example.randomusers.data.repository

import com.example.randomusers.data.local.UserDao
import com.example.randomusers.data.local.UserEntity
import com.example.randomusers.data.remote.UserApiService
import com.example.randomusers.data.remote.UserResponseDto
import com.example.randomusers.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


// Реализация репозитория для работы с данными пользователей
class UserRepositoryImp @Inject constructor(
    private val userApiService: UserApiService,
    private val userDao: UserDao
) : UserRepository {

    // Получение списка случайных пользователей
    override suspend fun getRandomUsers(count: Int): UserResponseDto {
        return userApiService.getRandomUsers(count)
    }

    // Сохранение списка пользователей в базу данных
    override suspend fun addAllUser(users: List<UserEntity>) {
        return userDao.addAllUser(users)
    }

    // Получение сохранённых пользователей из базы данных
    override fun getSavedUsers(): Flow<List<UserEntity>> {
        return userDao.getSavedUsers()
    }

    // Удаление всех пользователей из базы данных
    override suspend fun deleteAllUsers() {
        return userDao.deleteAllUsers()
    }
}