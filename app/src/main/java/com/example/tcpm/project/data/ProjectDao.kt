package com.example.tcpm.project.data

import kotlinx.coroutines.flow.Flow

interface ProjectDao {

    suspend fun addProject(project: Project)

    suspend fun getProject(projectId: String): Project

    suspend fun getProjectIds(): Flow<List<String>>

    suspend fun updateProject(project: Project)

    suspend fun deleteProject(projectId: String)

}