package com.dgdevelop.platzigram.post.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.FileProvider
import com.dgdevelop.platzigram.R
import com.dgdevelop.platzigram.model.Picture
import com.dgdevelop.platzigram.view.adapters.PictureAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    companion object{
        private const val REQUEST_CAMERA = 1
    }

    private lateinit var photoPathTemp: String
    private var photoFile: File? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showToolbar( resources.getString(R.string.tab_home), false, view)

        val pictureAdapter = activity?.let {
            PictureAdapter(it,buildPictures() ,R.layout.cardview_picture)
        }

        rvPicture.adapter = pictureAdapter

        fabCamera.setOnClickListener {
            takePicture()
        }
    }

    private fun takePicture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            context?.packageManager?.let { packageManager ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    try {
                        photoFile = createImageFile()
                    } catch (ex: IOException) {
                    }
                    photoFile?.also { file ->
                        context?.let {
                            val photoUri: Uri = FileProvider.getUriForFile(
                                it,
                                "com.dgdevelop.platzigram.fileprovider",
                                file
                            )
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                        }
                        startActivityForResult(takePictureIntent, REQUEST_CAMERA)
                    }
                }
            }
        }
    }

    private fun createImageFile(): File? {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HH-mm-ss", Locale.ROOT).format(Date())
        val imageFileName = "JPEG_${timeStamp}_"
        val storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, ".jpg", storageDir).apply {
            photoPathTemp = "file:$absolutePath"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CAMERA && resultCode == RESULT_OK){
            val intent = Intent(requireActivity(), NewPostActivity::class.java)
            Log.d("HomeFragment", "Camera Ok Path: $photoPathTemp")
            intent.putExtra("PHOTO_PATH_TEMP", photoPathTemp)
            startActivity(intent)
        }
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