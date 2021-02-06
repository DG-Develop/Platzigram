package com.dgdevelop.platzigram.login.presenter

interface LoginPresenter {
    fun signIn(username: String, password: String)
    fun loginSuccess()
    fun loginError(error: String)
}