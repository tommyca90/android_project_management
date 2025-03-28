package com.example.tcpm.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tcpm.navigation.data.NavManager
import com.example.tcpm.authentication.presentation.AuthenticationViewModel
import com.example.tcpm.R

@Composable
fun HomeScreen(navManager: NavManager, authViewModel: AuthenticationViewModel) {
    ScreenAuthenticated(
        navManager = navManager,
        authViewModel = authViewModel,
        title = stringResource(R.string.title_user_projects),
        navigationIconType = NavigationIconType.DRAWER,
        floatingActionButton = {
            IconButton(
                modifier = Modifier
                    .background(
                        color = colorResource(R.color.theme_green),
                        RoundedCornerShape(6.dp)
                    ),
                onClick = { navManager.navigateToAddProject() }
            ) {
                Icon(
                    modifier = Modifier.background(color = colorResource(R.color.theme_green)),
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.title_project_add),
                    tint = Color.White
                )
            }
        }
    )
}