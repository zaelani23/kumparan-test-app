package com.zaelani.kumparantestapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Address(

    @field:SerializedName("zipcode")
    val zipcode: String,

    @field:SerializedName("suite")
    val suite: String,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("street")
    val street: String
)
