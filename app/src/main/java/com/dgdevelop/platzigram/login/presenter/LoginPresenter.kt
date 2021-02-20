package com.dgdevelop.platzigram.login.presenter

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth

interface LoginPresenter {
    fun signIn(username: String, password: String, activity: Activity, firebaseAuth: FirebaseAuth)
    fun loginSuccess()
    fun loginError(error: String)
}