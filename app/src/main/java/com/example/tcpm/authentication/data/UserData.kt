package com.example.tcpm.authentication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(val userId: String = "", val email: String = "", val username: String = "", val imageUrl: String = ""): Parcelable