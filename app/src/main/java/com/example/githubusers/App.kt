package com.example.githubusers

import android.app.Application
import android.content.Context
import com.example.githubusers.di.AppComponent
import com.example.githubusers.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.create()
        super.onCreate()
    }
}

val Context.app
    get() = applicationContext as App