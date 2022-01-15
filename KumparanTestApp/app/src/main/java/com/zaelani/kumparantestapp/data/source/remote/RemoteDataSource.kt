package com.zaelani.kumparantestapp.data.source.remote

import android.util.Log
import com.zaelani.kumparantestapp.data.source.remote.response.*
import com.zaelani.kumparantestapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getAllPosts(callback : LoadPostsCallback){
        val client = ApiConfig.getApiService().getAllPosts()
        client.enqueue(object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                response.body()?.let { callback.onPostsLoaded(it) }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("RemoteDataSource", "getAllPost onFailure : ${t.message}")
            }

        })
    }

    fun getPostComments(callback : LoadCommentsCallback, postId : Int){
        val client = ApiConfig.getApiService().getPostComments(postId)
        client.enqueue(object : Callback<List<Comment>>{
            override fun onResponse(
                call: Call<List<Comment>>,
                response: Response<List<Comment>>
            ) {
                response.body()?.let { callback.onCommentsLoaded(it) }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.e("RemoteDataSource", "getPostComments onFailure : ${t.message}")
            }

        })
    }

    fun getPost(callback : LoadPostCallback, postId: Int){
        val client = ApiConfig.getApiService().getPost(postId)
        client.enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                response.body()?.let { callback.onPostLoaded(it) }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.e("RemoteDataSource", "getPost onFailure : ${t.message}")
            }
        })
    }

    fun getAllUsers(callback : LoadAllUsersCallback){
        val client = ApiConfig.getApiService().getAllUsers()
        client.enqueue(object : Callback<List<User>>{
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                response.body()?.let { callback.onUsersLoaded(it) }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("RemoteDataSource", "getAllUsers onFailure : ${t.message}")
            }

        })
    }

    fun getUser(callback : LoadUserCallback, userId : Int){
        val client = ApiConfig.getApiService().getUser(userId)
        client.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.body()?.let { callback.onUserLoaded(it) }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("RemoteDataSource", "getUser onFailure : ${t.message}")
            }

        })
    }

    fun getAlbums(callback : LoadAlbumsCallback, userId : Int){
        val client = ApiConfig.getApiService().getAlbums(userId)
        client.enqueue(object : Callback<List<Album>>{
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                response.body()?.let { callback.onAlbumLoaded(it) }
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                Log.e("RemoteDataSource", "getAlbums onFailure : ${t.message}")
            }

        })
    }

    fun getPhotos(callback : LoadPhotosCallback, albumId : Int){
        val client = ApiConfig.getApiService().getPhotos(albumId)
        client.enqueue(object : Callback<List<Photo>>{
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                response.body()?.let { callback.onPhotosLoaded(it) }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.e("RemoteDataSource", "getAlbums onFailure : ${t.message}")
            }

        })
    }

    fun getPhoto(callback : LoadPhotoCallback, photoId : Int){
        val client = ApiConfig.getApiService().getPhoto(photoId)
        client.enqueue(object : Callback<Photo>{
            override fun onResponse(call: Call<Photo>, response: Response<Photo>) {
                response.body()?.let { callback.onPhotoLoaded(it) }
            }

            override fun onFailure(call: Call<Photo>, t: Throwable) {
                Log.e("RemoteDataSource", "getPhoto onFailure : ${t.message}")
            }

        })
    }

    interface LoadPostsCallback{
        fun onPostsLoaded(posts : List<Post>)
    }

    interface LoadCommentsCallback{
        fun onCommentsLoaded(comments : List<Comment>)
    }

    interface LoadPostCallback{
        fun onPostLoaded(post: Post)
    }

    interface LoadAllUsersCallback{
        fun onUsersLoaded(users: List<User>)
    }

    interface LoadUserCallback{
        fun onUserLoaded(user : User)
    }

    interface LoadAlbumsCallback{
        fun onAlbumLoaded(albums : List<Album>)
    }

    interface LoadPhotosCallback{
        fun onPhotosLoaded(photos: List<Photo>)
    }

    interface LoadPhotoCallback{
        fun onPhotoLoaded(photo : Photo)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
}

