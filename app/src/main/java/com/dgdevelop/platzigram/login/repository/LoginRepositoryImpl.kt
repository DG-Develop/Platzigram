package com.dgdevelop.platzigram.login.repository

import android.app.Activity
import com.dgdevelop.platzigram.login.presenter.LoginPresenter
import com.google.firebase.auth.FirebaseAuth

class LoginRepositoryImpl(private val presenter: LoginPresenter): LoginRepository {



    override fun signIn(username: String, password: String, activity: Activity, firebaseAuth: FirebaseAuth) {
        firebaseAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    presenter.loginSuccess()
                } else {
                    presenter.loginError("Ocurrio un error")
                }
            }
    }
}