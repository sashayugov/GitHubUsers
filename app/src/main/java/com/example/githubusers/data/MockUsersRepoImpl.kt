package com.example.githubusers.data

import com.example.githubusers.domain.*

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

    override fun getReposData(userReposUrl: String): ReposData {
        val repos = Array(20) {
            RepoResponseModel(
                1,
                "some repository"
            )
        }
        return ReposData.Success(repos)
    }
}