package com.example.randomusers.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.randomusers.R
import com.example.randomusers.domain.User
import com.example.randomusers.ui.screens.DetailScreen
import com.example.randomusers.ui.screens.HomeScreen
import com.example.randomusers.ui.viewmodel.UserUiState
import com.example.randomusers.ui.viewmodel.UserViewModel


// NavHost приложения
@Composable
fun RandomUsersNavHost(
    navController: NavHostController = rememberNavController(),
    viewModel: UserViewModel,
    userUiState: UserUiState,
    retryAction: () -> Unit,
    onRefresh: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // Главный экран со списком пользователей
        composable(Screen.Home.route) {
            HomeScreen(
                userUiState = userUiState,
                onUserCardClick = { user ->
                    viewModel.changeSelectedUser(user)
                    navController.navigate(Screen.Detail.route)
                },
                onRefresh = onRefresh,
                retryAction = retryAction,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Экран с деталями выбранного пользователя
        composable(Screen.Detail.route) {
            val user: User? = viewModel.selectedUser.collectAsState().value
            if (user == null) {
                Text(stringResource(R.string.user_data_is_missing), modifier = Modifier.fillMaxSize())
            } else {
                DetailScreen(user = user)
            }
        }
    }
}