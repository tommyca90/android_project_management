package com.example.tcpm.user.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val userId: String = "", val email: String = "", val username: String = "", val imageUrl: String = ""): Parcelable