package com.zaelani.kumparantestapp.ui.listphoto

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.zaelani.kumparantestapp.data.source.remote.response.Photo
import com.zaelani.kumparantestapp.databinding.ItemsPhotoBinding
import com.zaelani.kumparantestapp.ui.detailphoto.DetailPhotoActivity


class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {
    private var listPhotos = ArrayList<Photo>()

    fun setPhotos(photos: List<Photo>) {
        this.listPhotos.clear()
        this.listPhotos.addAll(photos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosAdapter.PhotosViewHolder {
        val itemsPhotoBinding = ItemsPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotosViewHolder(itemsPhotoBinding)
    }

    override fun onBindViewHolder(holder: PhotosAdapter.PhotosViewHolder, position: Int) {
        val photo = listPhotos[position]
        holder.bind(photo)
    }

    override fun getItemCount(): Int {
        return listPhotos.size
    }

    inner class PhotosViewHolder(private val binding: ItemsPhotoBinding): RecyclerView.ViewHolder(
        binding.root
    ){
        fun bind(photo: Photo) {
            with(binding){
                val url = GlideUrl(
                    photo.thumbnailUrl, LazyHeaders.Builder()
                        .addHeader("User-Agent", "your-user-agent")
                        .build()
                )
                Glide.with(itemView.context)
                        .load(url)
                        .apply(RequestOptions())
                        .into(imgItemPhoto)
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailPhotoActivity::class.java)
                    intent.putExtra(DetailPhotoActivity.EXTRA_PHOTO_ID, photo.id)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }
}