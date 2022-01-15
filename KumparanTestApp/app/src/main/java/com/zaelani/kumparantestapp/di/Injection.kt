package com.zaelani.kumparantestapp.di

import android.content.Context
import com.zaelani.kumparantestapp.data.source.AppDataRepository
import com.zaelani.kumparantestapp.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): AppDataRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return AppDataRepository.getInstance(remoteDataSource)
    }
}