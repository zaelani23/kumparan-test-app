package com.zaelani.kumparantestapp.ui.userdetail

import androidx.lifecycle.ViewModel
import com.zaelani.kumparantestapp.data.source.AppDataRepository

class UserDetailViewModel(private val appDataRepository: AppDataRepository) : ViewModel() {
    fun getUser(userId : Int) = appDataRepository.getUser(userId)
    fun getAlbums(userId: Int) = appDataRepository.getAlbums(userId)
}