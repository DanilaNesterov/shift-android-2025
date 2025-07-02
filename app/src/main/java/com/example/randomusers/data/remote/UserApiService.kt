package com.example.randomusers.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

// Интерфейс для сетевого API
interface UserApiService {

    // Выполняет GET-запрос для получения указанного количества пользователей
    @GET("api/")
    suspend fun getRandomUsers(@Query("results") count: Int): UserResponseDto

}