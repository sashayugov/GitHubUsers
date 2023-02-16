package com.example.githubusers.domain

import com.google.gson.annotations.SerializedName

data class UserResponseModel(

    @SerializedName("id")
    val id: Int,

    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("repos_url")
    val reposUrl: String
)
