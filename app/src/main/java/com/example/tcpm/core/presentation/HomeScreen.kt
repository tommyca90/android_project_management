package com.example.tcpm.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tcpm.navigation.data.NavManager
import com.example.tcpm.authentication.presentation.AuthenticationViewModel
import com.example.tcpm.R
import com.example.tcpm.core.presentation.controllers.SnackbarController
import com.example.tcpm.core.presentation.controllers.SnackbarEvent
import com.example.tcpm.project.presentation.ProjectTile
import com.example.tcpm.project.presentation.ProjectsViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navManager: NavManager,
    authViewModel: AuthenticationViewModel,
    projectsViewModel: ProjectsViewModel
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val projectInfo by SnackbarController.projectEvents.collectAsStateWithLifecycle(SnackbarEvent(""))
    val projects by projectsViewModel.projects.collectAsStateWithLifecycle()

    LaunchedEffect(projectInfo) {
        if (projectInfo.message == "") {
            return@LaunchedEffect
        }
        scope.launch {
            snackbarHostState.showSnackbar(projectInfo.message)
        }
    }

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
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        content = {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(projects.count()) { ProjectTile(projects[it]) }
            }
        }
    )
}