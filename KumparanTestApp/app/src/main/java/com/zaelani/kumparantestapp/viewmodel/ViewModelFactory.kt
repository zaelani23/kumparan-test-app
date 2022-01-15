package com.zaelani.kumparantestapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zaelani.kumparantestapp.data.source.AppDataRepository
import com.zaelani.kumparantestapp.di.Injection
import com.zaelani.kumparantestapp.ui.detailphoto.DetailPhotoViewModel
import com.zaelani.kumparantestapp.ui.detailpost.DetailPostViewModel
import com.zaelani.kumparantestapp.ui.home.HomeViewModel
import com.zaelani.kumparantestapp.ui.listphoto.PhotosViewModel
import com.zaelani.kumparantestapp.ui.userdetail.DetailUserActivity
import com.zaelani.kumparantestapp.ui.userdetail.UserDetailViewModel

class ViewModelFactory private constructor(private val appDataRepository: AppDataRepository) : ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(appDataRepository) as T
            }
            modelClass.isAssignableFrom(DetailPostViewModel::class.java) -> {
                DetailPostViewModel(appDataRepository) as T
            }
            modelClass.isAssignableFrom(UserDetailViewModel::class.java) -> {
                UserDetailViewModel(appDataRepository) as T
            }
            modelClass.isAssignableFrom(PhotosViewModel::class.java) -> {
                PhotosViewModel(appDataRepository) as T
            }
            modelClass.isAssignableFrom(DetailPhotoViewModel::class.java) -> {
                DetailPhotoViewModel(appDataRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}