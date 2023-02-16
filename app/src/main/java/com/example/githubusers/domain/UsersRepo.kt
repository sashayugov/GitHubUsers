package com.example.githubusers.domain

interface UsersRepo {
    fun getUsersRepo(callback: (UsersData) -> Unit)

    fun getReposData(userReposUrl: String, callback: (ReposData) -> Unit)
}