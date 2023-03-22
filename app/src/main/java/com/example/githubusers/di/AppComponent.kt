package com.example.githubusers.di

import com.example.githubusers.data.RetrofitUsersRepoImpl
import com.example.githubusers.ui.reposlist.ReposListFragment
import com.example.githubusers.ui.userslist.UsersListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(usersListFragment: UsersListFragment)
    fun inject(reposListFragment: ReposListFragment)
}