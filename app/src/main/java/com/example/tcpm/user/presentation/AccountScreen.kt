package com.example.tcpm.user.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.tcpm.navigation.data.NavManager
import com.example.tcpm.R
import com.example.tcpm.authentication.presentation.AuthenticationViewModel
import com.example.tcpm.core.presentation.NavigationIconType
import com.example.tcpm.core.presentation.ScreenAuthenticated
import com.example.tcpm.user.data.User

@Composable
fun AccountScreen(navManager: NavManager, authViewModel: AuthenticationViewModel) {
    val user by authViewModel.user.collectAsState()

    ScreenAuthenticated(
        navManager = navManager,
        authViewModel = authViewModel,
        title = stringResource(R.string.title_user_account),
        navigationIconType = NavigationIconType.DRAWER
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .height(IntrinsicSize.Min)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EditAvatar(user)
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                LabelTextEdit(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.title_email),
                    text = user.email,
                    onChangeText = {},
                    isEditable = false
                )
                Spacer(modifier = Modifier.height(8.dp))
                LabelTextEdit(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.title_username),
                    text = user.username,
                    onChangeText = {},
                    isEditable = false
                )
            }
        }
    }
}

@Composable
fun EditAvatar(user: User) {
    val iconSize = 256.dp
    Column(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (user.imageUrl.isBlank()) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = stringResource(R.string.desc_icon_avatar),
                modifier = Modifier.size(iconSize)
            )
        } else {
            Image(
                painter = rememberAsyncImagePainter(user.imageUrl),
                contentDescription = stringResource(R.string.desc_icon_avatar),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(iconSize)
                    .clip(shape = RoundedCornerShape(50)),
            )
        }
    }
}

@Composable
fun LabelTextEdit(
    title: String,
    text: String,
    modifier: Modifier = Modifier,
    isEditable: Boolean = true,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = colorResource(R.color.theme_green),
        focusedLabelColor = colorResource(R.color.theme_green)
    ),
    onChangeText: (String) -> Unit
) {
    Text(title)
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = text,
        onValueChange = onChangeText,
        modifier = modifier,
        colors = colors,
        enabled = isEditable
    )
}