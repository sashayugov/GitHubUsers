package com.example.githubusers.domain

interface UsersRepo {
    fun getUsersRepo(): UsersData

    fun getRepoData(): RepoData
}