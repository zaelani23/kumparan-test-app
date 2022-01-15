package com.zaelani.kumparantestapp.ui.listphoto

import androidx.lifecycle.ViewModel
import com.zaelani.kumparantestapp.data.source.AppDataRepository

class PhotosViewModel(private val appDataRepository: AppDataRepository) : ViewModel() {
    fun getPhotos(albumId : Int) = appDataRepository.getPhotos(albumId)
}