package com.zaelani.kumparantestapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zaelani.kumparantestapp.data.source.remote.RemoteDataSource
import com.zaelani.kumparantestapp.data.source.remote.response.*

class AppDataRepository private constructor(
    val remoteDataSource: RemoteDataSource
    ) : AppDataSource{
    override fun getAllPosts(): LiveData<List<Post>> {
        val postsResult = MutableLiveData<List<Post>>()

        remoteDataSource.getAllPosts(object : RemoteDataSource.LoadPostsCallback{
            override fun onPostsLoaded(posts: List<Post>) {
                val postList = ArrayList<Post>()
                for (response in posts){
                    with(response){
                        val post = Post(
                            id,
                            title,
                            body,
                            userId
                        )
                        postList.add(post)
                    }
                }
                postsResult.postValue(postList)
            }
        })
        return postsResult
    }

    override fun getPost(postId: Int): LiveData<Post> {
        val userPost = MutableLiveData<Post>()

        remoteDataSource.getPost(object : RemoteDataSource.LoadPostCallback{
            override fun onPostLoaded(post: Post) {
                userPost.postValue(post)
            }

        }, postId)
        return userPost
    }

    override fun getPostComments(postId : Int): LiveData<List<Comment>> {
        val commentsResult = MutableLiveData<List<Comment>>()

        remoteDataSource.getPostComments(object : RemoteDataSource.LoadCommentsCallback{
            override fun onCommentsLoaded(comments: List<Comment>) {
                val commentList = ArrayList<Comment>()
                for (response in comments){
                    with(response){
                        val comment = Comment(
                            name, postId, id, body, email
                        )
                        commentList.add(comment)
                    }
                }
                commentsResult.postValue(commentList)
            }

        }, postId)
        return commentsResult
    }

    override fun getAllUsers(): LiveData<List<User>> {
        val usersResult = MutableLiveData<List<User>>()

        remoteDataSource.getAllUsers(object : RemoteDataSource.LoadAllUsersCallback{
            override fun onUsersLoaded(users: List<User>) {
                val usersList = ArrayList<User>()
                for (response in users){
                    with(response){
                        val user = User(
                            website, address, phone, name, company, id, email, username
                        )
                        usersList.add(user)
                    }
                }
                usersResult.postValue(usersList)
            }
        })
        return usersResult
    }

    override fun getUser(userId: Int): LiveData<User> {
        val userData = MutableLiveData<User>()

        remoteDataSource.getUser(object : RemoteDataSource.LoadUserCallback{
            override fun onUserLoaded(user: User) {
                userData.postValue(user)
            }

        }, userId)
        return userData
    }

    override fun getAlbums(userId: Int): LiveData<List<Album>> {
        val albumsResult = MutableLiveData<List<Album>>()

        remoteDataSource.getAlbums(object : RemoteDataSource.LoadAlbumsCallback{
            override fun onAlbumLoaded(albums: List<Album>) {
                val albumList = ArrayList<Album>()
                for(response in albums){
                    with(response){
                        val album = Album(
                                id, title, userId
                        )
                        albumList.add(album)
                    }
                }
                albumsResult.postValue(albumList)
            }
        }, userId)
        return albumsResult
    }

    override fun getPhotos(albumId: Int): LiveData<List<Photo>> {
        val photoResult = MutableLiveData<List<Photo>>()

        remoteDataSource.getPhotos(object : RemoteDataSource.LoadPhotosCallback{
            override fun onPhotosLoaded(photos: List<Photo>) {
                val photoList = ArrayList<Photo>()
                for (response in photos){
                    with(response){
                        val photo = Photo(
                                albumId, id, title, url, thumbnailUrl
                        )
                        photoList.add(photo)
                    }
                }
                photoResult.postValue(photoList)
            }
        }, albumId)
        return photoResult
    }

    override fun getPhoto(photoId: Int): LiveData<Photo> {
        val userPhoto = MutableLiveData<Photo>()

        remoteDataSource.getPhoto(object : RemoteDataSource.LoadPhotoCallback{
            override fun onPhotoLoaded(photo: Photo) {
                userPhoto.postValue(photo)
            }

        }, photoId)
        return userPhoto
    }

    companion object {
        @Volatile
        private var instance: AppDataRepository? = null
        fun getInstance(remoteData: RemoteDataSource): AppDataRepository =
            instance ?: synchronized(this) {
                instance ?: AppDataRepository(remoteData)
            }
    }
}