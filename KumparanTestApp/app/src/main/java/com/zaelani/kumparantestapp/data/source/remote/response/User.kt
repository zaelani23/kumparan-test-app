package com.zaelani.kumparantestapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class User(

	@field:SerializedName("website")
	val website: String,

	@field:SerializedName("address")
	val address: Address,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("company")
	val company: UserCompany,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
