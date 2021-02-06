package com.dgdevelop.platzigram.login.presenter

import com.dgdevelop.platzigram.login.interactor.LoginInteractor
import com.dgdevelop.platzigram.login.interactor.LoginInteractorImpl
import com.dgdevelop.platzigram.login.view.LoginView

class LoginPresenterImpl(private val loginView: LoginView): LoginPresenter {

    private val interactor: LoginInteractor = LoginInteractorImpl(this)

    override fun signIn(username: String, password: String) {
        loginView.disableInputs()
        loginView.showProgressBar()
        interactor.signIn(username, password)
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