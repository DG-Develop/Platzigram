package com.dgdevelop.platzigram.view.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.transition.Explode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.dgdevelop.platzigram.R
import com.dgdevelop.platzigram.model.Picture
import com.dgdevelop.platzigram.view.PictureDetailActivity
import com.squareup.picasso.Picasso

class PictureAdapter(
    private val context: Activity,
    private val pictures: ArrayList<Picture>,
    private val resources: Int
) : RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder =
        PictureViewHolder(
            LayoutInflater.from(context).inflate(resources, parent, false)
        )

    override fun getItemCount(): Int = pictures.size

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val picture = pictures[position]

        holder.usernameCard.text = picture.username
        holder.timeCard.text = picture.time
        holder.likeNumberCard.text = picture.like_number
        Picasso.get().load(picture.picture).into(holder.pictureCard)

        holder.pictureCard.setOnClickListener {
            val intent = Intent(context, PictureDetailActivity::class.java)

            val explode = Explode()
            explode.duration = 1000
            context.window.exitTransition = explode
            context.startActivity(
                intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    context, it, context.getString(R.string.transitionname_picture)
                ).toBundle()
            )
        }
    }

    inner class PictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pictureCard = itemView.findViewById<ImageView>(R.id.ivPictureCard)
        val usernameCard = itemView.findViewById<TextView>(R.id.tvUsernameCard)
        val timeCard = itemView.findViewById<TextView>(R.id.tvTimeCard)
        val likeNumberCard = itemView.findViewById<TextView>(R.id.tvLikeNumberCard)
    }
}