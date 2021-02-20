package com.dgdevelop.platzigram.login.presenter

import android.app.Activity
import com.dgdevelop.platzigram.login.interactor.LoginInteractor
import com.dgdevelop.platzigram.login.interactor.LoginInteractorImpl
import com.dgdevelop.platzigram.login.view.LoginView
import com.google.firebase.auth.FirebaseAuth

class LoginPresenterImpl(private val loginView: LoginView): LoginPresenter {

    private val interactor: LoginInteractor = LoginInteractorImpl(this)

    override fun signIn(username: String, password: String, activity: Activity, firebaseAuth: FirebaseAuth) {
        loginView.disableInputs()
        loginView.showProgressBar()
        interactor.signIn(username, password, activity, firebaseAuth)
    }


    override fun loginSuccess() {
        loginView.enableInputs()
        loginView.hideProgressBar()
        loginView.goPrincipalHome()
    }

    override fun loginError(error: String) {
        loginView.enableInputs()
        loginView.hideProgressBar()
        loginView.loginError(error)
    }

}