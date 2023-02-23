package com.example.githubusers.data

import com.example.githubusers.data.retrofit.GitHubRetrofitService
import com.example.githubusers.domain.*
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    private var _api: GitHubRetrofitService =
        retrofitGitHub.create(GitHubRetrofitService::class.java)
    override var api = _api

    override fun getUsersRepo(callback: (UsersData) -> Unit) {

        _api.listGitHubUsers().enqueue(object : Callback<Array<UserResponseModel>> {
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

    override fun getReposData(userName: String, callback: (ReposData) -> Unit) {
        _api.listRepos(userName).enqueue(object : Callback<Array<RepoResponseModel>> {
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

    override fun getUsersDataByObservable(): Single<UsersData> {
        val single: Single<UsersData> = Single.create {
            it.onSuccess(doApiUsersResponse())
        }
        return single
    }

    private fun doApiUsersResponse(): UsersData {
        val body = _api.listGitHubUsers().execute().body()
        return if (body.isNullOrEmpty()) {
            UsersData.Error(Throwable("Error, no users data"))
        } else UsersData.Success(body)
    }

    override fun getReposDataByObservable(userName: String): Single<ReposData> {
        val single: Single<ReposData> = Single.create {
            it.onSuccess(doApiReposResponse(userName))
        }
        return single
    }

    private fun doApiReposResponse(userName: String): ReposData {
        val body = _api.listRepos(userName).execute().body()
        return if (body.isNullOrEmpty()) {
            ReposData.Error(Throwable("Error, no users data"))
        } else ReposData.Success(body)
    }
}