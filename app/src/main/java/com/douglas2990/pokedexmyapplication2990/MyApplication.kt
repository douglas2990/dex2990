package com.douglas2990.pokedexmyapplication2990

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}