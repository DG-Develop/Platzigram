package com.dgdevelop.platzigram.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dgdevelop.platzigram.R
import com.dgdevelop.platzigram.model.Picture
import com.dgdevelop.platzigram.view.adapters.PictureAdapter
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showToolbar("Busqueda", false, view)

        val pictureAdapter = PictureAdapter(requireActivity(),buildPictures() ,R.layout.cardview_picture)

        rvPictureSearch.adapter = pictureAdapter
    }

    fun buildPictures(): ArrayList<Picture>{
        val pictures = ArrayList<Picture>()

        pictures.add(
            Picture(
                "https://mott.pe/noticias/wp-content/uploads/2018/08/3-ajustes-de-lightroom-para-darle-dramatismo-a-las-fotos-de-paisaje.png",
                "David Gómez",
                "4 días",
                "3 Me gusta"
            )
        )

        pictures.add(
            Picture(
                "https://cdn.pixabay.com/photo/2015/06/19/21/24/the-road-815297_1280.jpg",
                "Monica Hernandez",
                "2 días",
                "10 Me gusta"
            )
        )

        pictures.add(
            Picture(
                "https://cdn.pixabay.com/photo/2015/02/24/15/41/dog-647528_1280.jpg",
                "Roberto Cardenaz",
                "6 días",
                "2 Me gusta"
            )
        )

        return pictures
    }

    private fun showToolbar(title: String, upButton: Boolean, view: View){
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val activitySupport = activity as AppCompatActivity
        activitySupport.setSupportActionBar(toolbar!!)
        activitySupport.supportActionBar?.title = title
        activitySupport.supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
    }

}