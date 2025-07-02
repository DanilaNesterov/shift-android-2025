package com.example.randomusers.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Интерфейс DAO для операций в базе данных
@Dao
interface UserDao {

    // Вставка списка пользователей
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllUser(users: List<UserEntity>)

    // Удаление всех пользователей из таблицы
    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    // Получение списка сохранённых пользователей
    @Query("SELECT * FROM users")
    fun getSavedUsers(): Flow<List<UserEntity>>
}