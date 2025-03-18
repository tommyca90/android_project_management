package com.example.tcpm.authentication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthUser(val isAuthenticated: Boolean = false, val userId: String = "", val email: String = ""): Parcelable