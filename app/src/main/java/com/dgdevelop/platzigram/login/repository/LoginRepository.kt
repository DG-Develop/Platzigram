package com.dgdevelop.platzigram.login.repository

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth

interface LoginRepository {
    fun signIn(username: String, password: String, activity: Activity, firebaseAuth: FirebaseAuth)
}