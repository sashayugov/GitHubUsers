package com.example.githubusers.repo

import com.example.githubusers.model.UserResponseModel
import com.example.githubusers.model.UsersData

class MockUsersRepoImpl : UsersRepo {
    override fun getUsersRepo(): UsersData {

        val users = Array(50) {
            UserResponseModel(
                1,
                "UserName",
                "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png",
                "https://api.github.com/users/mojombo/repos"
            )
        }
        return UsersData.Success(users)
    }
}