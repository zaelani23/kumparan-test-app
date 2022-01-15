package com.zaelani.kumparantestapp.ui.userdetail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zaelani.kumparantestapp.data.source.remote.response.Album
import com.zaelani.kumparantestapp.databinding.ItemsAlbumBinding
import com.zaelani.kumparantestapp.ui.listphoto.PhotosActivity

class AlbumAdapter: RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {
    private var listAlbum = ArrayList<Album>()

    fun setAlbums(albums : List<Album>){
        this.listAlbum.clear()
        this.listAlbum.addAll(albums)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.AlbumViewHolder {
        val itemsAlbumBinding = ItemsAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(itemsAlbumBinding)
    }

    override fun onBindViewHolder(holder: AlbumAdapter.AlbumViewHolder, position: Int) {
        val album = listAlbum[position]
        holder.bind(album)
    }

    override fun getItemCount(): Int = listAlbum.size

    inner class AlbumViewHolder(private val binding : ItemsAlbumBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(album: Album) {
            with(binding){
                tvAlbumName.text = album.title
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, PhotosActivity::class.java)
                    intent.putExtra(PhotosActivity.EXTRA_ALBUM_ID, album.id)
                    intent.putExtra(PhotosActivity.EXTRA_ALBUM_NAME, album.title)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }
}