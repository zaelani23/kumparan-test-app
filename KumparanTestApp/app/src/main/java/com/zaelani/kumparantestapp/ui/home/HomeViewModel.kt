package com.zaelani.kumparantestapp.ui.home

import androidx.lifecycle.ViewModel
import com.zaelani.kumparantestapp.data.source.AppDataRepository

class HomeViewModel(private val appDataRepository: AppDataRepository) : ViewModel() {
    fun getAllPosts() = appDataRepository.getAllPosts()
    fun getAllUsers() = appDataRepository.getAllUsers()
}