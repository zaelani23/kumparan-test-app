package com.zaelani.kumparantestapp.data.source

import androidx.lifecycle.LiveData
import com.zaelani.kumparantestapp.data.source.remote.response.*

interface AppDataSource {
    fun getAllPosts(): LiveData<List<Post>>
    fun getPost(postId : Int): LiveData<Post>
    fun getPostComments(postId : Int): LiveData<List<Comment>>
    fun getAllUsers(): LiveData<List<User>>
    fun getUser(userId : Int): LiveData<User>
    fun getAlbums(userId: Int): LiveData<List<Album>>
    fun getPhotos(albumId : Int): LiveData<List<Photo>>
    fun getPhoto(photoId : Int): LiveData<Photo>
}