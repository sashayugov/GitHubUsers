package com.example.githubusers.repo

import com.example.githubusers.model.UsersData

interface UsersRepo {
    fun getUsersRepo(): UsersData
}