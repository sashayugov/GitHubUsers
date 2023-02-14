package com.example.githubusers.repo

import androidx.lifecycle.LiveData
import com.example.githubusers.model.UserModel

interface UsersRepo {
    fun getUsersRepo(): LiveData<Array<UserModel>>
}