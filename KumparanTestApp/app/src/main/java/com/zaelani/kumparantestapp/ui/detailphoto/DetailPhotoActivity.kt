package com.zaelani.kumparantestapp.ui.detailphoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.zaelani.kumparantestapp.databinding.ActivityDetailPhotoBinding
import com.zaelani.kumparantestapp.viewmodel.ViewModelFactory

class DetailPhotoActivity : AppCompatActivity() {

    private var _activityDetailPhoto : ActivityDetailPhotoBinding? = null
    val binding get() = _activityDetailPhoto!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailPhoto = ActivityDetailPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionbar = supportActionBar
        actionbar?.title= "Detail Photo"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        showProgressBar(true)
        val photoId = intent.getIntExtra(EXTRA_PHOTO_ID, 0)
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailPhotoViewModel::class.java]
        viewModel.getPhoto(photoId).observe(this, {photo ->
            showProgressBar(false)
            with(binding){
                tvPhotoName.text = photo.title
                val url = GlideUrl(
                    photo.url, LazyHeaders.Builder()
                        .addHeader("User-Agent", "your-user-agent")
                        .build()
                )
                Glide.with(this@DetailPhotoActivity)
                    .load(url)
                    .apply(RequestOptions())
                    .into(imgPhoto)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityDetailPhoto = null
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showProgressBar(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    companion object{
        const val EXTRA_PHOTO_ID = "extra_photo_id"
    }
}