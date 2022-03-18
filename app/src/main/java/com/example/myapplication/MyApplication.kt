package com.example.myapplication

import android.app.Application
import android.util.Log
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.MapsInitializer.Renderer
import com.google.android.gms.maps.OnMapsSdkInitializedCallback

class MyApplication : Application(), OnMapsSdkInitializedCallback {

    override fun onCreate() {
        super.onCreate()
        MapsInitializer.initialize(applicationContext, Renderer.LATEST, this)
    }

    override fun onMapsSdkInitialized(renderer: Renderer) {
        when (renderer) {
            Renderer.LATEST -> Log.d("MapsDemo", "The latest version of the renderer is used.")
            Renderer.LEGACY -> Log.d("MapsDemo", "The legacy version of the renderer is used.")
        }
    }
}
