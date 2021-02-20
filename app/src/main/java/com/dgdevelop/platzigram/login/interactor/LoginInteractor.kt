package com.dgdevelop.platzigram.login.interactor

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth

interface LoginInteractor {
    fun signIn(username: String, password: String, activity: Activity, firebaseAuth: FirebaseAuth)
}