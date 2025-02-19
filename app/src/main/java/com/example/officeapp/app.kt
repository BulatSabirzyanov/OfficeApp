package com.example.officeapp

import android.app.Application
import com.example.officeapp.di.AppComponent
import com.example.officeapp.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = createAppComponent()
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}