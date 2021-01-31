package com.dgdevelop.platzigram.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.dgdevelop.platzigram.R
import kotlinx.android.synthetic.main.activity_container.*

class ContainerActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        configNav()
    }

    private fun configNav(){
        navController = Navigation.findNavController(this, R.id.fragContent)
        NavigationUI.setupWithNavController(bottombar, navController)
    }

}