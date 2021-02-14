package com.dgdevelop.platzigram.post.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dgdevelop.platzigram.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_new_post.*

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        val photoPath = intent.extras!!.getString("PHOTO_PATH_TEMP")
        Picasso.get().load(photoPath).into(ivPhoto)

    }
}