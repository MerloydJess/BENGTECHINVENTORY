package com.benguettech.inventory

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class InventoryApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this)
        }
    }
}
