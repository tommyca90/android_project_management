package com.example.tcpm.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.tcpm.navigation.data.NavManager
import com.example.tcpm.R
import com.example.tcpm.authentication.presentation.AuthenticationViewModel
import com.example.tcpm.user.data.User

data class NavigationItem(
    val title: String,
    val description: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val onClick: () -> Unit
)

@Composable
fun TCPMModalNavigationDrawer(
    navManager: NavManager,
    authViewModel: AuthenticationViewModel,
    drawerState: DrawerState = DrawerState(initialValue = DrawerValue.Closed),
    content: @Composable BoxScope.() -> Unit
) {
    val user by authViewModel.user.collectAsState()

    val navItems = listOf<NavigationItem>(
        NavigationItem(
            title = stringResource(R.string.title_home),
            description = stringResource(R.string.desc_icon_home),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            onClick = { navManager.navigateToHome() }
        ),
        NavigationItem(
            title = stringResource(R.string.title_own_account),
            description = stringResource(R.string.desc_icon_account),
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            onClick = { navManager.navigateToAccount() }
        ),
        NavigationItem(
            title = stringResource(R.string.title_logout),
            description = stringResource(R.string.desc_icon_exit),
            selectedIcon = Icons.AutoMirrored.Filled.ExitToApp,
            unselectedIcon = Icons.AutoMirrored.Outlined.ExitToApp,
            onClick = { authViewModel.logOutUser() }
        )
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = colorResource(R.color.theme_green),
                drawerContentColor = colorResource(R.color.theme_gray),
                modifier = Modifier.width(IntrinsicSize.Min)
            ) {
                DrawerAvatar(user)
                Column(
                    modifier = Modifier
                        .background(colorResource(R.color.theme_gray))
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    navItems.forEachIndexed { idx, navItem ->
                        val isSelected = idx == authViewModel.drawerNavigationIndex.value
                        NavigationDrawerItem(
                            label = { Text(text = navItem.title) },
                            selected = isSelected,
                            onClick = {
                                authViewModel.onChangeDrawerNavigationIndex(idx)
                                navItem.onClick()
                            },
                            icon = {
                                Icon(
                                    if (isSelected) navItem.selectedIcon else navItem.unselectedIcon,
                                    navItem.description
                                )
                            }
                        )
                    }
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
fun DrawerAvatar(user: User) {
    val iconSize = 128.dp
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
        Spacer(modifier = Modifier.height(8.dp))
        Text(user.username, style = MaterialTheme.typography.labelLarge)
    }
}