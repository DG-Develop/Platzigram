package com.dgdevelop.platzigram.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dgdevelop.platzigram.R
import kotlinx.android.synthetic.main.actionbar_toolbar.*

class CreateAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        showToolbar(resources.getString(R.string.toolbar_title_createaccount), true)
    }

    private fun showToolbar(title: String, upButton: Boolean){
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
    }
}