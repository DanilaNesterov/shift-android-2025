package com.example.randomusers.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


// Модель пользователя
@Parcelize
data class User(
    val id: String,           // Айди
    val fullName: String,     // Имя
    val gender: String,       // Пол
    val country: String,      // Страна
    val city: String,         // Город
    val street: String,       // Улица и номер дома
    val email: String,        // Электронная почта
    val postcode: String,     // Почтовый индекс
    val phone: String,        // Номер телефона
    val photoUrl: String,     // URL аватара
    val nationality: String,  // Национальность
    val dob: String,          // Дата рождения
    val registered: String    // Дата регистрации
) : Parcelable