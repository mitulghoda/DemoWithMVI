package com.appearnings.baseapp

import android.app.Application
import android.content.Context

class Controller : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: Controller
    }
}