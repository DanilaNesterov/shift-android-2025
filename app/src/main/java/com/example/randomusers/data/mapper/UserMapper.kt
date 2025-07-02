package com.example.randomusers.data.mapper

import com.example.randomusers.data.local.UserEntity
import com.example.randomusers.data.remote.UserDto
import com.example.randomusers.domain.User

// Конвертация из DTO в модель User
fun UserDto.toDomain(): User = User(
    id = this.login.uuid,
    fullName = "${this.name.first} ${this.name.last}",
    gender = this.gender,
    city = this.location.city,
    country = this.location.country,
    street = "${this.location.street.name} ${this.location.street.number}",
    email = this.email,
    phone = this.phone,
    photoUrl = this.picture.large,
    nationality = this.nationality,
    postcode = this.location.postcode,
    dob = this.dob.date.substringBefore("T"),
    registered = this.registered.date.substringBefore("T"),
)

// Конвертация из сущности базы данных UserEntity в модель User
fun UserEntity.toDomain(): User = User(
    id = this.id,
    fullName = this.fullName,
    gender = this.gender,
    country = this.country,
    city = this.city,
    street = this.street,
    email = this.email,
    phone = this.phone,
    photoUrl = this.photoUrl,
    nationality = this.nationality,
    postcode = this.postcode,
    dob = this.dob,
    registered = this.registered
)

// Конвертация из модели User в сущность базы данных UserEntity
fun User.toEntity(): UserEntity = UserEntity(
    id = this.id,
    fullName = this.fullName,
    gender = this.gender,
    country = this.country,
    city = this.city,
    street = this.street,
    email = this.email,
    phone = this.phone,
    photoUrl = this.photoUrl,
    nationality = this.nationality,
    postcode = this.postcode,
    dob = this.dob,
    registered = this.registered
)