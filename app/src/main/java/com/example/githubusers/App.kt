package com.example.githubusers

import android.app.Application
import android.content.Context
import com.example.githubusers.di.appModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        startKoin {
            androidLogger()
            modules(appModule)
        }
        super.onCreate()
    }
}

val Context.app
    get() = applicationContext as App