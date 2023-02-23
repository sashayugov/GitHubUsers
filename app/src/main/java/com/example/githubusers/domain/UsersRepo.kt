package com.example.githubusers.domain

import com.example.githubusers.data.retrofit.GitHubRetrofitService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface UsersRepo {
    var api: GitHubRetrofitService

    fun getUsersRepo(callback: (UsersData) -> Unit)

    fun getReposData(userName: String, callback: (ReposData) -> Unit)

    fun getUsersDataByObservable(): Single<UsersData>

    fun getReposDataByObservable(userName: String): Single<ReposData>
}