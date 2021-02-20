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
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var presenter: LoginPresenter

    companion object{
        private const val TAG = "Login"
    }

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    private lateinit var  callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        callbackManager = CallbackManager.Factory.create()

        firebaseAuth = FirebaseAuth.getInstance()
        authStateListener = FirebaseAuth.AuthStateListener {
            val firebaseUser = firebaseAuth.currentUser
            if(firebaseUser != null){
                Log.w(TAG, "Usuario logeado " + firebaseUser.email)
                goPrincipalHome()
            }else{
                Log.w(TAG, "Usuario logeado")
            }
        }

        presenter = LoginPresenterImpl(this)

        btnLogin.setOnClickListener {
            val username = tietUsername.text.toString()
            val password = tietPassword.text.toString()

            if(username.isBlank() || password.isBlank()){
                Snackbar.make(btnLogin, "No deje campos vacios", Snackbar.LENGTH_LONG).show()
                Log.i("Login", "No dejes campos vacios")
                return@setOnClickListener
            }
            sigIn(tietUsername.text.toString(), tietPassword.text.toString())

        }

        // Facebook
        login_facebook.setPermissions(listOf("email", "public_profile"))
        login_facebook.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Log.w(TAG, "Facebook Login Success Token: ${result?.accessToken?.token}")
                firebaseAuthWithFacebook(result!!.accessToken)
            }

            override fun onCancel() {
                Log.w(TAG, "Facebook Cancel")
            }

            override fun onError(error: FacebookException?) {
                Log.w(TAG, "Facebook Error: ${error?.message}")
            }
        })


        ivLogo.setOnClickListener {
            goToHomePage()
        }

        btn_create_here.setOnClickListener {
            goCreateAccount()
        }

    }

    private fun firebaseAuthWithFacebook(accessToken: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(accessToken.token)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener {task ->
                if(task.isSuccessful){
                    Log.d(TAG, "signInWithCredential:success")
                    Snackbar.make(login_facebook, "Facebook Authentication Success", Snackbar.LENGTH_SHORT).show()
                    goPrincipalHome()
                    finish()
                }else{
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Snackbar.make(login_facebook, "Facebook Authentication Failed", Snackbar.LENGTH_SHORT).show()
                }
            }
    }

    private fun sigIn(email: String, password: String) {
        presenter.signIn(email, password, this, firebaseAuth)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data) // Facebook
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(authStateListener)
    }

}