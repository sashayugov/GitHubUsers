package com.example.githubusers.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubusers.data.RetrofitUsersRepoImpl
import com.example.githubusers.domain.UsersRepo
import com.example.githubusers.ui.UsersListViewModel
import javax.inject.Inject
import javax.inject.Singleton

class ViewModelFactory @Inject constructor (private val usersRepo: UsersRepo) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersListViewModel(usersRepo) as T
    }
}