package com.zaelani.kumparantestapp.ui.detailphoto

import androidx.lifecycle.ViewModel
import com.zaelani.kumparantestapp.data.source.AppDataRepository

class DetailPhotoViewModel(private val appDataRepository: AppDataRepository) : ViewModel() {
    fun getPhoto(photoId : Int) = appDataRepository.getPhoto(photoId)
}