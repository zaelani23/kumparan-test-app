package com.zaelani.kumparantestapp.ui.listphoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.zaelani.kumparantestapp.databinding.ActivityPhotosBinding
import com.zaelani.kumparantestapp.viewmodel.ViewModelFactory

class PhotosActivity : AppCompatActivity() {

    private var _activityPhotosBinding : ActivityPhotosBinding? = null
    val binding get() = _activityPhotosBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityPhotosBinding = ActivityPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionbar = supportActionBar
        actionbar?.title= "Album"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        showProgressBar(true)
        val albumId = intent.getIntExtra(EXTRA_ALBUM_ID, 0)
        val albumTitle = intent.getStringExtra(EXTRA_ALBUM_NAME)
        val photosAdapter = PhotosAdapter()
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[PhotosViewModel::class.java]
        viewModel.getPhotos(albumId).observe(this, {photos ->
            showProgressBar(false)
            photosAdapter.setPhotos(photos)
            photosAdapter.notifyDataSetChanged()
        })

        binding.tvAlbumName.text = albumTitle
        with(binding.rvPhoto){
            layoutManager = GridLayoutManager(context, 3)
            setHasFixedSize(true)
            adapter = photosAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityPhotosBinding = null
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showProgressBar(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    companion object{
        const val EXTRA_ALBUM_ID = "extra_album_id"
        const val EXTRA_ALBUM_NAME = "extra_album_name"
    }
}