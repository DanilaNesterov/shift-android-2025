package com.example.randomusers.di

import android.content.Context
import androidx.room.Room
import com.example.randomusers.data.local.UserDatabase
import com.example.randomusers.data.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


// Модуль для создания и предоставления базы данных и DAO
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user_database.db"
        )
            .fallbackToDestructiveMigration(false)
            .build()

    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase): UserDao = database.userDao()

}