package com.example.githubusers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.domain.ReposData
import com.example.githubusers.domain.UsersData
import com.example.githubusers.domain.UsersRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class UsersListViewModel(private val usersRepo: UsersRepo) : ViewModel() {

    private var _usersLiveData: MutableLiveData<UsersData> = MutableLiveData()
    val usersLiveData = _usersLiveData as LiveData<UsersData>

    private var _reposLiveData: MutableLiveData<ReposData> = MutableLiveData()
    val reposLiveData = _reposLiveData as LiveData<ReposData>

    private var disposableUsers: Disposable? = null
    private var disposableRepos: Disposable? = null

    init {
        _usersLiveData.value = UsersData.Loading(true)
        _reposLiveData.value = ReposData.Loading(true)

        getUsersData()
    }

    private fun getUsersData() {
//        __Retrofit_Call_enqueue__
//        usersRepo.getUsersRepo {
//            _usersLiveData.value = it
//        }

//        __Retrofit_Call_execute+Observable__
//       disposableUsers = usersRepo.getUsersDataByObservable()
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy(
//                onSuccess = { usersData -> _usersLiveData.value = usersData },
//                onError = { throwable -> _usersLiveData.value = UsersData.Error(throwable) }
//            )

//        __Rx_Retrofit__
        disposableUsers = usersRepo.api.listGithubUsersByRx()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { users -> _usersLiveData.value = UsersData.Success(users) },
                onError = { throwable -> _usersLiveData.value = UsersData.Error(throwable) }
            )
    }

    fun getReposByName(userName: String) {
//        __Retrofit_Call_enqueue__
//        usersRepo.getReposData(userName) {
//            _reposLiveData.value = it
//        }

//        __Retrofit_Call_execute+Observable__
//       disposableRepos = usersRepo.getReposDataByObservable(userName)
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy(
//                onSuccess = { reposData -> _reposLiveData.value = reposData },
//                onError = { throwable -> _reposLiveData.value = ReposData.Error(throwable) }
//            )

//        __Rx_Retrofit__
        disposableRepos = usersRepo.api.listReposByRx(userName)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { repos -> _reposLiveData.value = ReposData.Success(repos) },
                onError = { throwable -> _reposLiveData.value = ReposData.Error(throwable) }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposableUsers?.dispose()
        disposableRepos?.dispose()
    }
}
