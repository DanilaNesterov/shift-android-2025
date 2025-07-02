package com.example.randomusers.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomusers.data.mapper.toDomain
import com.example.randomusers.data.mapper.toEntity
import com.example.randomusers.domain.User
import com.example.randomusers.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


// Состояния UI
sealed interface UserUiState {
    data class Success(val users: List<User>) : UserUiState
    class Error(val errorMessage: String) : UserUiState
    object Loading : UserUiState
}

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    // Выбранный пользователь
    private val _selectedUser: MutableStateFlow<User?> = MutableStateFlow(null)
    val selectedUser: StateFlow<User?> = _selectedUser.asStateFlow()

    // Состояние UI
    private val _userUiState: MutableStateFlow<UserUiState> = MutableStateFlow(UserUiState.Loading)
    val userUiState: StateFlow<UserUiState> = _userUiState.asStateFlow()

    init {
        loadSavedUsers()
    }

    // Изменение выбранного пользователя
    fun changeSelectedUser(user: User) {
        _selectedUser.value = user
    }

    // Обновление пользователей из API
    fun refreshUsers() {
        viewModelScope.launch {
            _userUiState.value = UserUiState.Loading
            try {
                val result = userRepository.getRandomUsers(20)
                val newUsers = result.results.map { it.toDomain() }
                userRepository.deleteAllUsers()
                userRepository.addAllUser(newUsers.map { it.toEntity() })
                _userUiState.value = UserUiState.Success(users = newUsers)
            } catch (e: IOException) {
                _userUiState.value = UserUiState.Error("No internet connection")
            } catch (e: HttpException) {
                _userUiState.value = UserUiState.Error("Server error: ${e.code()}")
            } catch (e: Exception) {
                _userUiState.value = UserUiState.Error("Unknown error occurred")
            }
        }
    }

    // Загрузка пользователей из локальной базы
    fun loadSavedUsers() {
        viewModelScope.launch {
            _userUiState.value = UserUiState.Loading
            try {
                val savedUsers = userRepository.getSavedUsers().first()
                if (savedUsers.isEmpty()) {
                    refreshUsers()
                } else {
                    _userUiState.value = UserUiState.Success(users = savedUsers.map { it.toDomain() })
                }
            } catch (e: IOException) {
                _userUiState.value = UserUiState.Error("No internet and no cached data")
            } catch (e: Exception) {
                _userUiState.value = UserUiState.Error("Error loading cached data")
            }
        }
    }
}