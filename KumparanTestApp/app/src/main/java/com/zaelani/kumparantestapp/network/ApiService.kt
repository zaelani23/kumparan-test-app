package com.zaelani.kumparantestapp.network

import com.zaelani.kumparantestapp.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService  {

    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

    @GET("posts/{postId}")
    fun getPost(
        @Path("postId") postId: Int
    ): Call<Post>

    @GET("posts/{postId}/comments")
    fun getPostComments(
        @Path("postId") postId: Int
    ): Call<List<Comment>>

    @GET("users")
    fun getAllUsers(): Call<List<User>>

    @GET("users/{userId}")
    fun getUser(
        @Path("userId") userId: Int
    ): Call<User>

    @GET("albums")
    fun getAlbums(
            @Query("userId") userId : Int
    ) : Call<List<Album>>

    @GET("photos")
    fun getPhotos(
            @Query("albumId") albumId : Int
    ): Call<List<Photo>>

    @GET("photos/{photoId}")
    fun getPhoto(
        @Path("photoId") photoId : Int
    ): Call<Photo>
}