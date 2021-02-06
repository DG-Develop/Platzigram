package com.dgdevelop.platzigram.login.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.dgdevelop.platzigram.R
import com.dgdevelop.platzigram.login.presenter.LoginPresenter
import com.dgdevelop.platzigram.login.presenter.LoginPresenterImpl
import com.dgdevelop.platzigram.view.ContainerActivity
import com.dgdevelop.platzigram.view.CreateAccountActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenterImpl(this)

        btnLogin.setOnClickListener {
            val username = tietUsername.text.toString()
            val password = tietPassword.text.toString()

            if(username.isBlank() || password.isBlank()){
                Snackbar.make(btnLogin, "No deje campos vacios", Snackbar.LENGTH_LONG).show()
                Log.i("Login", "No dejes campos vacios")
                return@setOnClickListener
            }
            presenter.signIn(tietUsername.text.toString(), tietPassword.text.toString())
        }

        ivLogo.setOnClickListener {
            goToHomePage()
        }

    }

    override fun enableInputs() {
        tietUsername.isEnabled = true
        tietPassword.isEnabled = true
        btnLogin.isEnabled = true
        btn_create_here.isEnabled = true
    }

    override fun disableInputs() {
        tietUsername.isEnabled = false
        tietPassword.isEnabled = false
        btnLogin.isEnabled = false
        btn_create_here.isEnabled = false
    }

    override fun showProgressBar() {
        pbLogin.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        pbLogin.visibility = View.GONE
    }

    override fun loginError(error: String) {
        Snackbar.make(btnLogin, error, Snackbar.LENGTH_LONG).show()
    }

    override fun goToHomePage(){
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/DG-Develop")))
    }

    override fun goCreateAccount(){
        startActivity(Intent(this, CreateAccountActivity::class.java))
    }

    override fun goPrincipalHome(){
        startActivity(Intent(this, ContainerActivity::class.java))
    }

}