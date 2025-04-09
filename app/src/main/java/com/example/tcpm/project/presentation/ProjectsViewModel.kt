package com.example.tcpm.project.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcpm.core.data.Graph
import com.example.tcpm.project.data.Project
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProjectsViewModel : ViewModel() {
    val _projectRepository = Graph.projectRepository

    private val _projects = MutableStateFlow(emptyList<Project>())
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()

    private var job: Job? = null

    fun init(){
        job = viewModelScope.launch {
            _projectRepository.getProjectIds().collect { projectIds ->
                var tmpProjects = mutableListOf<Project>()
                projectIds.forEach { projectId ->
                        val project = _projectRepository.getProjectById(projectId)
                        tmpProjects.add(project)
                }
                _projects.value = tmpProjects
            }
        }
    }

    fun reset(){
        job?.cancel()
        _projects.value = emptyList<Project>()
    }
}