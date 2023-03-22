package com.example.githubusers.di

import com.example.githubusers.data.RetrofitUsersRepoImpl
import com.example.githubusers.data.retrofit.GitHubRetrofitService
import com.example.githubusers.domain.UsersRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.github.com/"

@Module(includes = [AppNetworkModule::class, AppBindModule::class])
class AppModule

@Module
class AppNetworkModule {
    @Singleton
    @Provides
    fun providesRetrofitService(): GitHubRetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(GitHubRetrofitService::class.java)
    }
}

@Module
interface AppBindModule {
    @Singleton
    @Binds
    fun provideUserRepo(retrofitUsersRepoImpl: RetrofitUsersRepoImpl): UsersRepo
}