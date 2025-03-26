package com.example.tcpm.project.data

import kotlinx.coroutines.flow.Flow
import java.time.Instant.now

class ProjectRepository(private val projectDao: ProjectDao) {

    private fun assertValidProjectId(projectId: String){
        if(projectId == "") {
            throw Exception("invalid project id")
        }
    }

    suspend fun addProject(
        title: String,
        description: String,
        imgUrl: String = ""
    ) {
        val project = Project(
            title = title,
            description = description,
            imgUrl = imgUrl,
            dateCreated = now().epochSecond,
            )
        projectDao.addProject(project)
    }

    suspend fun getProjectById(projectId: String): Project {
        assertValidProjectId(projectId)
        return projectDao.getProject(projectId)
    }

    suspend fun getProjectIds(): Flow<List<String>>{
        return projectDao.getProjectIds()
    }

    suspend fun updateProject(project: Project) {
        assertValidProjectId(project.id)
        projectDao.updateProject(project)
    }

    suspend fun deleteProject(projectId: String) {
        assertValidProjectId(projectId)
        projectDao.deleteProject(projectId)
    }
}