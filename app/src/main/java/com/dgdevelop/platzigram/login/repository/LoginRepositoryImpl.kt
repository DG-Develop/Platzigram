package com.dgdevelop.platzigram.login.repository

import com.dgdevelop.platzigram.login.presenter.LoginPresenter

class LoginRepositoryImpl(private val presenter: LoginPresenter): LoginRepository {

    override fun signIn(username: String, password: String) {
        val success = true
        if(success){
            presenter.loginSuccess()
        }else{
            presenter.loginError("Ocurrio un error")
        }
    }
}