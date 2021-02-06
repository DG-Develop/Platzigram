package com.dgdevelop.platzigram.login.interactor

import com.dgdevelop.platzigram.login.presenter.LoginPresenter
import com.dgdevelop.platzigram.login.repository.LoginRepositoryImpl

class LoginInteractorImpl(loginPresenter: LoginPresenter): LoginInteractor {

    private val repository = LoginRepositoryImpl(loginPresenter)

    override fun signIn(username: String, password: String) =
        repository.signIn(username, password)

}