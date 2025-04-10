package com.example.tcpm.project.presentation

import androidx.compose.foundation.layout.Box
import com.example.tcpm.R
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.DoneOutline
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.tcpm.authentication.presentation.AuthenticationViewModel
import com.example.tcpm.core.presentation.NavigationIconType
import com.example.tcpm.core.presentation.ScreenAuthenticated
import com.example.tcpm.navigation.data.NavManager
import kotlinx.coroutines.launch

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val description: String
)

@Composable
fun ProjectScreen(
    navManager: NavManager,
    authViewModel: AuthenticationViewModel,
    projectViewModel: ProjectViewModel
) {
    val tabItems = listOf(
        TabItem(
            title = LocalContext.current.resources.getString(R.string.title_todo),
            unselectedIcon = Icons.Outlined.AddBox,
            selectedIcon = Icons.Filled.AddBox,
            description = LocalContext.current.resources.getString(R.string.desc_todo)
        ),
        TabItem(
            title = LocalContext.current.resources.getString(R.string.title_in_progress),
            unselectedIcon = Icons.Outlined.Edit,
            selectedIcon = Icons.Filled.Edit,
            description = LocalContext.current.resources.getString(R.string.desc_in_progress)
        ),
        TabItem(
            title = LocalContext.current.resources.getString(R.string.title_done),
            unselectedIcon = Icons.Outlined.DoneOutline,
            selectedIcon = Icons.Filled.Done,
            description = LocalContext.current.resources.getString(R.string.desc_done)
        ),
    )
    val name = projectViewModel.name.collectAsStateWithLifecycle()
    var pagerState = rememberPagerState { tabItems.size }
    var scope = rememberCoroutineScope()

    ScreenAuthenticated(
        navManager = navManager,
        authViewModel = authViewModel,
        title = name.value,
        navigationIconType = NavigationIconType.BACK,
        bottomBar = {
            ScrollableTabRow(
                selectedTabIndex = pagerState.targetPage,
                contentColor = colorResource(R.color.theme_green),
                indicator = {}
            ) {
                tabItems.forEachIndexed { idx, item ->
                    Tab(
                        selected = pagerState.targetPage == idx,
                        onClick = { scope.launch { pagerState.animateScrollToPage(idx) } },
                        text = { Text(text = item.title) },
                        icon = {
                            Icon(
                                imageVector = if (pagerState.targetPage == idx) item.selectedIcon else item.unselectedIcon,
                                contentDescription = ""
                            )
                        },
                        unselectedContentColor = Color.Gray
                    )
                }
            }
        }
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) { index ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(tabItems[index].title)
            }
        }
    }
}