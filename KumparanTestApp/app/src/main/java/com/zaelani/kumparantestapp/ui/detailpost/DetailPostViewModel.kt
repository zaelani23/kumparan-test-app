package com.zaelani.kumparantestapp.ui.detailpost

import androidx.lifecycle.ViewModel
import com.zaelani.kumparantestapp.data.source.AppDataRepository
import com.zaelani.kumparantestapp.data.source.remote.response.User

class DetailPostViewModel(private val appDataRepository: AppDataRepository) : ViewModel() {
    fun getAllUsers() = appDataRepository.getAllUsers()
    fun getPost(postId : Int) = appDataRepository.getPost(postId)
    fun getPostComments(postId: Int) = appDataRepository.getPostComments(postId)
}