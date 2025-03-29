package com.example.tcpm.core.presentation.controllers

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

data class SnackbarEvent(
    val message: String,
    val action: SnackbarAction? = null
)

data class SnackbarAction(
    val name: String,
    val action: () -> Unit,
)

object SnackbarController {
    private val _projectEvents = Channel<SnackbarEvent>()
    val projectEvents = _projectEvents.receiveAsFlow()

    suspend fun sendProjectEvent(event: SnackbarEvent){
        _projectEvents.send(event)
    }
}