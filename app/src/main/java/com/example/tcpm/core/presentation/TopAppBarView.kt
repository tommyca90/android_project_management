package com.example.tcpm.core.presentation

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.tcpm.R

enum class NavigationIconType {
    DRAWER, BACK, NONE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(
    title: String = "",
    navigationIconType: NavigationIconType = NavigationIconType.NONE,
    onNavigationItemClicked: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 28.dp)
            )
        },
        colors = TopAppBarColors(
            containerColor = colorResource(R.color.theme_green),
            scrolledContainerColor = colorResource(R.color.theme_green),
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
        ),
        navigationIcon = {
            if (navigationIconType != NavigationIconType.NONE) {
                IconButton(onClick = { onNavigationItemClicked() }) {
                    when (navigationIconType) {
                        NavigationIconType.DRAWER ->
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu"
                            )

                        NavigationIconType.BACK ->
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back Arrow"
                            )

                        else -> {}
                    }
                }
            }
        })
}