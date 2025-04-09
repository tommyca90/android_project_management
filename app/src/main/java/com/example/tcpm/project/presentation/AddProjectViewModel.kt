package com.example.tcpm.project.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcpm.core.data.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddProjectViewModel() : ViewModel() {
    private val _pjRepo = Graph.projectRepository

    private val _title = mutableStateOf("")
    val title: State<String> = _title
    private val _description = mutableStateOf("")
    val description: State<String> = _description
    private val _isCreating = mutableStateOf(false)
    val isCreating: State<Boolean> = _isCreating

    fun reset(){
        _title.value = ""
        _description.value = ""
        _isCreating.value = false
    }

    fun onChangeTitle(title: String) {
        _title.value = title
    }

    fun onChangeDescription(description: String) {
        _description.value = description
    }

    fun createProject(onProjectCreated: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            _isCreating.value = true
            _pjRepo.addProject(title = _title.value, description = _description.value)
            _isCreating.value = false
            onProjectCreated()
        }
    }

}