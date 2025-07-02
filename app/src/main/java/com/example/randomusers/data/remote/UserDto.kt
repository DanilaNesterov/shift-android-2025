package com.example.randomusers.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


// DTO классы для десериализации ответа от API

@Serializable
data class UserResponseDto(
    val results: List<UserDto>,
    val info: InfoDto
)

@Serializable
data class UserDto(
    val gender: String,
    val name: NameDto,
    val location: LocationDto,
    val email: String,
    val login: LoginDto,
    val phone: String,
    val cell: String,
    val picture: PictureDto,
    @SerialName(value = "nat")
    val nationality: String,
    val dob: DobDto,
    val registered: RegisteredDto
)

@Serializable
data class RegisteredDto(
    val date: String,
    val age: String
)

@Serializable
data class DobDto(
    val date: String,
    val age: Int
)

@Serializable
data class LoginDto(
    val uuid: String
)

@Serializable
data class PictureDto(
    val large: String,
    val medium: String,
    val thumbnail: String
)


@Serializable
data class NameDto(
    val title: String,
    val first: String,
    val last: String
)

@Serializable
data class LocationDto(
    val street: StreetDto,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesDto,
    val timezone: TimezoneDto
)

@Serializable
data class TimezoneDto(
    val offset: String,
    val description: String
)

@Serializable
data class CoordinatesDto(
    val latitude: String,
    val longitude: String,
)

@Serializable
data class StreetDto(
    val number: Int,
    val name: String
)

@Serializable
data class InfoDto(
    val seed: String,
    val results: Int,
    val page: Int
)