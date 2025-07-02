package com.example.randomusers.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.randomusers.domain.User
import com.example.randomusers.ui.viewmodel.UserUiState


// Основной экран приложения
@Composable
fun HomeScreen(
    userUiState: UserUiState,
    onUserCardClick: (User) -> Unit,
    onRefresh: () -> Unit,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (userUiState) {
        is UserUiState.Success -> {
            UserListScreen(
                users = userUiState.users,
                onUserCardClick = onUserCardClick,
                onRefresh = onRefresh,
                modifier = modifier
            )
        }
        is UserUiState.Loading -> LoadingScreen(modifier = modifier)
        is UserUiState.Error -> ErrorScreen(
            retryAction = retryAction,
            errorMessage = userUiState.errorMessage,
            modifier = modifier
        )
    }
}