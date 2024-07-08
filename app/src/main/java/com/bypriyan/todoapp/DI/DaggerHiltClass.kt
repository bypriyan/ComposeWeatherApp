package com.bypriyan.togocartuser.DI

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DaggerHiltClass: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}