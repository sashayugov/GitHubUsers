package com.example.githubusers.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubusers.model.UserModel

class MockUsersRepoImpl : UsersRepo {
    override fun getUsersRepo(): LiveData<Array<UserModel>> {

        val liveData: MutableLiveData<Array<UserModel>> = MutableLiveData()

        val users = Array(50) {
            UserModel(
                1,
                "UserName",
                "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png",
                "https://api.github.com/users/mojombo/repos"
            )
        }

        liveData.postValue(users)
        return liveData
    }
}