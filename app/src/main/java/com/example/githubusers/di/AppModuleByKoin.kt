package com.example.githubusers.di

import com.example.githubusers.data.RetrofitUsersRepoImpl
import com.example.githubusers.data.retrofit.GitHubRetrofitService
import com.example.githubusers.domain.UsersRepo
import com.example.githubusers.ui.UsersListViewModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

val appModule = module {
    single<GitHubRetrofitService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(GitHubRetrofitService::class.java)
    }

    single<UsersRepo> {
        RetrofitUsersRepoImpl(_api = get())
    }

    viewModel {
        UsersListViewModel(usersRepo = get())
    }
}
