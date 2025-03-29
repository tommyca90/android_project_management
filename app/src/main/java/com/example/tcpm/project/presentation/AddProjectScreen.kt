package com.example.tcpm.project.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tcpm.R
import com.example.tcpm.authentication.presentation.AuthenticationViewModel
import com.example.tcpm.core.presentation.NavigationIconType
import com.example.tcpm.core.presentation.ScreenAuthenticated
import com.example.tcpm.core.presentation.buttons.RoundedOutlineTextButton
import com.example.tcpm.core.presentation.buttons.RoundedTextButton
import com.example.tcpm.core.presentation.controllers.SnackbarController
import com.example.tcpm.core.presentation.controllers.SnackbarEvent
import com.example.tcpm.navigation.data.NavManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AddProjectScreen(
    navManager: NavManager,
    authViewModel: AuthenticationViewModel,
    addProjectViewModel: AddProjectViewModel
) {
    ScreenAuthenticated(
        navManager = navManager,
        authViewModel = authViewModel,
        title = stringResource(R.string.title_project_add),
        navigationIconType = NavigationIconType.BACK
    ) {
        val scope = rememberCoroutineScope()
        val modifierOutlinedTextFields = Modifier.fillMaxWidth()
        val configuredColors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.theme_green),
            focusedLabelColor = colorResource(R.color.theme_green)
        )
        val textAdded = LocalContext.current.resources.getString(R.string.desc_project_added)

        fun navigateBackAndResetUserInput() {
            addProjectViewModel.reset()
            navManager.navigateToHome()
        }

        Column(modifier = Modifier.padding(6.dp)) {
            OutlinedTextField(
                modifier = modifierOutlinedTextFields,
                value = addProjectViewModel.title.value,
                onValueChange = { addProjectViewModel.onChangeTitle(it) },
                label = { Text(stringResource(R.string.title_project_title)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = configuredColors
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                modifier = modifierOutlinedTextFields,
                value = addProjectViewModel.description.value,
                onValueChange = { addProjectViewModel.onChangeDescription(it) },
                label = { Text(stringResource(R.string.title_description)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = configuredColors
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row() {
                RoundedOutlineTextButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.interaction_cancel),
                    onClick = { navigateBackAndResetUserInput() })
                Spacer(modifier = Modifier.width(6.dp))
                RoundedTextButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.interaction_create),
                    onClick = {
                        addProjectViewModel.createProject({
                            val title = addProjectViewModel.title.value
                            scope.launch(Dispatchers.Main) {
                                SnackbarController.sendProjectEvent(SnackbarEvent(  "$textAdded $title"))
                            }
                            navigateBackAndResetUserInput()
                        })
                    },
                    showWaitIndicator = addProjectViewModel.isCreating.value
                )
            }
        }
    }
}