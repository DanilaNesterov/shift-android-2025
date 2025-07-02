package com.example.randomusers.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.randomusers.domain.User

// Список пользователей
@Composable
fun UserList(
    users: List<User>,
    onUserCardClick: (User) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(users) {user ->
            UserCard(
                user = user,
                onUserCardClick = { onUserCardClick(user) },
            )
        }
    }
}