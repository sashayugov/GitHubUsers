package com.example.githubusers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.domain.UsersData
import com.example.githubusers.data.RetrofitUsersRepoImpl
import com.example.githubusers.domain.ReposData
import com.example.githubusers.domain.UsersRepo

class UsersListViewModel(
    private val usersRepo: UsersRepo = RetrofitUsersRepoImpl()
) : ViewModel() {

    private var _usersLiveData: MutableLiveData<UsersData> = MutableLiveData()
    val usersLiveData = _usersLiveData as LiveData<UsersData>

    private var _reposLiveData: MutableLiveData<ReposData> = MutableLiveData()
    val reposLiveData = _reposLiveData as LiveData<ReposData>

    init {
        _usersLiveData.value = UsersData.Loading(true)
        _reposLiveData.value = ReposData.Loading(true)

        getUsersData()
    }

    private fun getUsersData() {
        usersRepo.getUsersRepo {
            _usersLiveData.value = it
        }
    }

    fun getReposByName(userName: String) {
        usersRepo.getReposData(userName) {
            _reposLiveData.value = it
        }
    }
}
