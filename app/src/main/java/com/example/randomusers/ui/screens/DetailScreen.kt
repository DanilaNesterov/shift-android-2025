package com.example.randomusers.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.randomusers.R
import com.example.randomusers.domain.User
import com.example.randomusers.ui.components.ClickableInfoRow
import com.example.randomusers.ui.components.InfoRow


// Экран с детальной информацией о пользователе
@Composable
fun DetailScreen(
    user: User,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    val addressUri = "geo:0,0?q=${Uri.encode("${user.street}, ${user.city}, ${user.postcode}, ${user.country}")}"


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Аватар и имя
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                model = user.photoUrl,
                contentDescription = stringResource(R.string.avatar_of, user.fullName),
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.loading_img),
                error = painterResource(R.drawable.ic_broken_image)
            )
            Text(
                text = user.fullName,
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
        }

        HorizontalDivider()

        // Основная информация
        Text(stringResource(R.string.basic_information), style = MaterialTheme.typography.titleLarge)
        InfoRow(
            icon = Icons.Default.Person,
            label = stringResource(R.string.gender),
            value = user.gender.replaceFirstChar { it.uppercase() }
        )

        InfoRow(
            icon = Icons.Default.Flag,
            label = stringResource(R.string.nationality),
            value = user.nationality.uppercase()
        )

        InfoRow(
            icon = Icons.Default.CalendarToday,
            label = stringResource(R.string.date_of_birth),
            value = user.dob
        )

        InfoRow(
            icon = Icons.Default.EventAvailable,
            label = stringResource(R.string.registered),
            value = user.registered
        )

        HorizontalDivider()

        // Контакты
        Text(stringResource(R.string.contact_information), style = MaterialTheme.typography.titleLarge)

        ClickableInfoRow(
            icon = Icons.Default.Email,
            label = stringResource(R.string.email),
            value = user.email,
            onClick = { uriHandler.openUri("mailto:${user.email}") }
        )

        ClickableInfoRow(
            icon = Icons.Default.Call,
            label = stringResource(R.string.phone),
            value = user.phone,
            onClick = { uriHandler.openUri("tel:${user.phone}") }
        )

        HorizontalDivider()

        // Адрес
        Text(stringResource(R.string.address), style = MaterialTheme.typography.titleLarge)
        ClickableInfoRow(
            icon = Icons.Default.LocationOn,
            label = stringResource(R.string.address),
            value = "${user.street}, ${user.city}, ${user.postcode}, ${user.country}",
            onClick = { uriHandler.openUri(addressUri) }
        )
    }
}
