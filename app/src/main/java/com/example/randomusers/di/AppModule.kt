package com.example.randomusers.di

import com.example.randomusers.data.local.UserDao
import com.example.randomusers.data.remote.UserApiService
import com.example.randomusers.data.repository.UserRepositoryImp
import com.example.randomusers.domain.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


// Модуль для предоставления UserRepository
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesUserRepository(
        userApiService: UserApiService,
        userDao: UserDao
    ): UserRepository = UserRepositoryImp(userApiService, userDao)

}