package com.dgdevelop.platzigram.login.interactor

import android.app.Activity
import com.dgdevelop.platzigram.login.presenter.LoginPresenter
import com.dgdevelop.platzigram.login.repository.LoginRepositoryImpl
import com.google.firebase.auth.FirebaseAuth

class LoginInteractorImpl(loginPresenter: LoginPresenter): LoginInteractor {

    private val repository = LoginRepositoryImpl(loginPresenter)

    override fun signIn(username: String, password: String, activity: Activity, firebaseAuth: FirebaseAuth) =
        repository.signIn(username, password, activity, firebaseAuth)

}