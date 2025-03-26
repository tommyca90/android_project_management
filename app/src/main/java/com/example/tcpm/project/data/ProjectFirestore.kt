package com.example.tcpm.project.data

import com.example.tcpm.persistence.data.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProjectFirestore : ProjectDao {
    private val _firestore = FirebaseFirestore.getInstance()
    private val _firebaseAuth = FirebaseAuth.getInstance()

    override suspend fun addProject(project: Project) {
        withContext(Dispatchers.IO) {
            // create project itself
            val projectId = _firestore.collection(Constants.PROJECTS).document().id
            val ownerEmail = _firebaseAuth.currentUser?.email?: ""
            val updatedProject = project.copy(id = projectId, ownerEmail = ownerEmail)
            updateProject(updatedProject)

            // create project link for owner
            val data = hashMapOf(projectId to true)
            _firestore.collection(Constants.USER_PROJECTS)
                .document(getCurrentUserId())
                .set(data, SetOptions.merge()).await()
        }
    }

    override suspend fun getProject(projectId: String): Project {
        return withContext(Dispatchers.IO) {
            val document = _firestore.collection(Constants.PROJECTS)
                .document(projectId).get().await()
            document.toObject(Project::class.java) ?: Project()
        }
    }

    override suspend fun updateProject(project: Project) {
        withContext(Dispatchers.IO) {
            _firestore.collection(Constants.PROJECTS)
                .document(project.id)
                .set(project).await()
        }
    }

    override suspend fun deleteProject(projectId: String) {
        withContext(Dispatchers.IO) {
            _firestore.collection(Constants.PROJECTS)
                .document(projectId).delete().await()
        }
    }

    override suspend fun getProjectIds(): Flow<List<String>> = callbackFlow {
        // Reference to use in Firestore
        var docRef: DocumentReference? = null
        while(getCurrentUserId() == ""){
            delay(5000)
        }
        try {
            docRef = _firestore
                .collection(Constants.USER_PROJECTS)
                .document(getCurrentUserId())
        } catch (e: Throwable) {
            close(e)
        }
        // Registers callback to firestore, which will be called on new events
        val subscription = docRef?.addSnapshotListener { snapshot, _ ->
            if (snapshot == null) { return@addSnapshotListener }
            try {
                snapshot.data?.keys?.let { trySend(it.map { key -> key}) }
            } catch (e: Throwable) {
                // Event couldn't be sent to the flow
            }
        }
        awaitClose { subscription?.remove() }
    }

    private fun getCurrentUserId(): String {
        return FirebaseAuth.getInstance().currentUser?.uid?: ""
    }

}