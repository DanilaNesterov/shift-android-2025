package com.example.randomusers.ui.navigation

// Экраны приложения
sealed class Screen(val route: String) {
    data object Home : Screen("home")    // Главный экран
    data object Detail : Screen("detail") // Экран деталей
}