package com.example.githubusers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.domain.UserResponseModel
import com.example.githubusers.domain.UsersData
import com.example.githubusers.data.MockUsersRepoImpl

class UsersListViewModel(
    private val usersRepo: MockUsersRepoImpl = MockUsersRepoImpl()
) : ViewModel() {

    private var _usersLiveData: MutableLiveData<UsersData> = MutableLiveData()
    val usersLiveData = _usersLiveData as LiveData<UsersData>

    init {
        _usersLiveData.value = usersRepo.getUsersRepo()
    }

    fun onUserClick(item: UserResponseModel) {
        // TODO
    }
}
