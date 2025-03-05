package com.example.tcpm.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.tcpm.R
import com.example.tcpm.data.UserData
import com.example.tcpm.models.authentication.AuthenticationViewModel

@Composable
fun TCPMModalNavigationDrawer(
    authViewModel: AuthenticationViewModel,
    content: @Composable BoxScope.() -> Unit
) {
    val userData by authViewModel.userData

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = colorResource(R.color.theme_green),
                drawerContentColor = colorResource(R.color.theme_gray),
                modifier = Modifier.width(IntrinsicSize.Min)
            ) {
                DrawerAvatar(userData)
                Column(
                    modifier = Modifier
                        .background(colorResource(R.color.theme_gray))
                        .weight(1f)
                ) {
                    HorizontalDivider(thickness = 8.dp, color = colorResource(R.color.theme_gray))
                    NavigationDrawerItem(
                        label = { Text(text = stringResource(R.string.title_own_account)) },
                        selected = false,
                        onClick = { /*TODO*/ },
                        icon = { Icon(Icons.Filled.AccountCircle, stringResource(R.string.desc_icon_account)) }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = stringResource(R.string.title_logout)) },
                        selected = false,
                        onClick = { authViewModel.logOutUser() },
                        icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, stringResource(R.string.desc_icon_exit)) }
                    )
                }

            }
        }
    ) {
        Box {
            content(this)
        }
    }
}

@Composable
fun DrawerAvatar(userData: UserData) {
    val iconSize = 128.dp
    Column(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (userData.imageUrl.isBlank()) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = stringResource(R.string.desc_icon_avatar),
                modifier = Modifier.size(iconSize)
            )
        } else {
            Image(
                painter = rememberAsyncImagePainter(userData.imageUrl),
                contentDescription = stringResource(R.string.desc_icon_avatar),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(iconSize)
                    .clip(shape = RoundedCornerShape(50)),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(userData.username, style = MaterialTheme.typography.labelLarge)
    }
}