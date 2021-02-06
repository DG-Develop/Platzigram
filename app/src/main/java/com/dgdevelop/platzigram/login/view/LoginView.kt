package com.dgdevelop.platzigram.login.view

interface LoginView {

    fun enableInputs()
    fun disableInputs()

    fun showProgressBar()
    fun hideProgressBar()

    fun loginError(error: String)

    fun goToHomePage()
    fun goCreateAccount()
    fun goPrincipalHome()
}