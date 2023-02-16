package com.example.githubusers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.domain.UsersData
import com.example.githubusers.data.MockUsersRepoImpl
import com.example.githubusers.domain.ReposData

class UsersListViewModel(
    private val usersRepo: MockUsersRepoImpl = MockUsersRepoImpl()
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
        _usersLiveData.value = usersRepo.getUsersRepo()
    }

    fun getReposByUrl(userReposUrl: String) {
        _reposLiveData.value = usersRepo.getReposData(userReposUrl)
    }
}
