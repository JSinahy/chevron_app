package com.trend.chevron.data.di

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ExpertoChevronApplication: Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: ExpertoChevronApplication

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
    override fun onCreate() {
        super.onCreate()
    }
}