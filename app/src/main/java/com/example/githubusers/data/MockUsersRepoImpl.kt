package com.example.githubusers.data

import com.example.githubusers.data.retrofit.GitHubRetrofitService
import com.example.githubusers.domain.*
import io.reactivex.rxjava3.core.Single

class MockUsersRepoImpl(override var api: GitHubRetrofitService) : UsersRepo {
    override fun getUsersRepo(callback: (UsersData) -> Unit) {

        val users = Array(50) {
            UserResponseModel(
                1,
                "UserName",
                "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png",
                "https://api.github.com/users/mojombo/repos"
            )
        }
        callback.invoke(UsersData.Success(users))
    }

    override fun getReposData(userName: String, callback: (ReposData) -> Unit) {
        val repos = Array(20) {
            RepoResponseModel(
                1,
                "some repository"
            )
        }
        callback.invoke(ReposData.Success(repos))
    }

    override fun getUsersDataByObservable(): Single<UsersData> {
        TODO("Not yet implemented")
    }

    override fun getReposDataByObservable(userName: String): Single<ReposData> {
        TODO("Not yet implemented")
    }
}