package com.example.tcpm.project.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Project(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val dateCreated: Long = 0L,
    val imgUrl: String = "",
    val ownerEmail: String = ""
) : Parcelable
