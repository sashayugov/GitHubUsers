package com.example.githubusers.data.retrofit

import com.example.githubusers.domain.RepoResponseModel
import com.example.githubusers.domain.UserResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubRetrofitService {
    @GET("users")
    fun listGitHubUsers(): Call<Array<UserResponseModel>>

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<Array<RepoResponseModel>>
}