package com.dgdevelop.platzigram

import android.app.Application
import android.util.Log
import com.dgdevelop.platzigram.login.view.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class PlatzigramApplication: Application() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate() {
        super.onCreate()

        firebaseAuth = FirebaseAuth.getInstance()
        authStateListener = FirebaseAuth.AuthStateListener {
            val firebaseUser = firebaseAuth.currentUser
            if(firebaseUser != null){
                Log.w(TAG, "Usuario logeado " + firebaseUser.email)
            }else{
                Log.w(TAG, "Usuario logeado")
            }
        }
    }

    companion object{
        private const val TAG = "App"
    }

}