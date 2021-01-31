package com.dgdevelop.platzigram

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dgdevelop.platzigram.view.ContainerActivity
import com.dgdevelop.platzigram.view.CreateAccountActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ivLogo.setOnClickListener {
            goToHomePAge()
        }

    }

    private fun goToHomePAge(){
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/DG-Develop")))
    }

    fun goCreateAccount(view: View){
        startActivity(Intent(this, CreateAccountActivity::class.java))
    }

    fun goPrincipalHome(view: View){
        startActivity(Intent(this, ContainerActivity::class.java))
    }
}