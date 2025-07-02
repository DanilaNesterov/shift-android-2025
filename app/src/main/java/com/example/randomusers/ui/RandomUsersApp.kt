package com.example.randomusers.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.randomusers.ui.navigation.RandomUsersNavHost
import com.example.randomusers.ui.viewmodel.UserUiState
import com.example.randomusers.ui.viewmodel.UserViewModel

// Главная composable функция приложения
@Composable
fun RandomUsersApp(
    viewModel: UserViewModel = hiltViewModel()
) {
    val userUiState: UserUiState by viewModel.userUiState.collectAsState()
    RandomUsersNavHost(
        userUiState = userUiState,
        retryAction = { viewModel.refreshUsers() },
        onRefresh = { viewModel.refreshUsers() },
        viewModel = viewModel
    )
}