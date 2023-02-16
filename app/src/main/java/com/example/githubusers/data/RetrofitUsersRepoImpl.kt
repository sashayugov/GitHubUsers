package com.example.githubusers.data

import com.example.githubusers.data.retrofit.GitHubRetrofitService
import com.example.githubusers.domain.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

class RetrofitUsersRepoImpl : UsersRepo {

    private var retrofitGitHub: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private var api: GitHubRetrofitService =
        retrofitGitHub.create(GitHubRetrofitService::class.java)

    override fun getUsersRepo(callback: (UsersData) -> Unit) {

        api.listGitHubUsers().enqueue(object : Callback<Array<UserResponseModel>> {
            override fun onResponse(
                call: Call<Array<UserResponseModel>>, response: Response<Array<UserResponseModel>>
            ) {
                if (response.body().isNullOrEmpty()) {
                    callback(UsersData.Error(Throwable("Error, no users data")))
                } else {
                    callback(UsersData.Success(response.body() ?: emptyArray()))
                }
            }

            override fun onFailure(call: Call<Array<UserResponseModel>>, t: Throwable) {
                callback(UsersData.Error(t))
            }
        })
    }

    override fun getReposData(userReposUrl: String, callback: (ReposData) -> Unit) {
        api.listRepos(userReposUrl).enqueue(object : Callback<Array<RepoResponseModel>> {
            override fun onResponse(
                call: Call<Array<RepoResponseModel>>, response: Response<Array<RepoResponseModel>>
            ) {
                if (response.body().isNullOrEmpty()) {
                    callback(ReposData.Error(Throwable("Error, no repos data")))
                } else {
                    callback(ReposData.Success(response.body() ?: emptyArray()))
                }
            }

            override fun onFailure(call: Call<Array<RepoResponseModel>>, t: Throwable) {
                callback(ReposData.Error(t))
            }
        })
    }
}