package com.zaelani.kumparantestapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserCompany(

    @field:SerializedName("bs")
    val bs: String,

    @field:SerializedName("catchPhrase")
    val catchPhrase: String,

    @field:SerializedName("name")
    val name: String
)
