package com.example.githubusers.domain

import com.google.gson.annotations.SerializedName

data class RepoResponseModel(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)