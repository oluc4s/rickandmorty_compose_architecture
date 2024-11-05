package com.s2start.rickandmorty

import android.app.Application
import com.s2start.rickandmorty.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication: Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(appModule)
        }
    }
}