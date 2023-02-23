package com.example.githubusers.data.retrofit

import android.database.Observable
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService
import com.example.githubusers.domain.RepoResponseModel
import com.example.githubusers.domain.UserResponseModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubRetrofitService {
    @GET("users")
    fun listGitHubUsers(): Call<Array<UserResponseModel>>

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<Array<RepoResponseModel>>

    @GET("users")
    fun listGithubUsersByRx(): Single<Array<UserResponseModel>>

    @GET("users/{user}/repos")
    fun listReposByRx(@Path("user") user: String): Single<Array<RepoResponseModel>>
}