package com.dgdevelop.platzigram.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dgdevelop.platzigram.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.actionbar_toolbar.*
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        showToolbar(resources.getString(R.string.toolbar_title_createaccount), true)

        firebaseAuth = FirebaseAuth.getInstance()
        authStateListener = FirebaseAuth.AuthStateListener {
            val firebaseUser = firebaseAuth.currentUser
            if(firebaseUser != null){
                Log.w(TAG, "Usuario logeado " + firebaseUser.email)
            }else{
                Log.w(TAG, "Usuario logeado")
            }
        }

        btnJoinUs.setOnClickListener {
            val email = tietEmail.text.toString()
            val password = tietPassword_Createaccount.text.toString()
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Snackbar.make(it, "Cuenta creada exitosamente", Snackbar.LENGTH_SHORT).show()
                }else{
                    Snackbar.make(it, "Ocurrio un error al crear la cuenta", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun showToolbar(title: String, upButton: Boolean){
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(authStateListener)
    }

    companion object{
        private const val TAG ="CreateAccount"
    }
}