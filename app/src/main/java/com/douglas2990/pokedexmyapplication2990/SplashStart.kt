package com.douglas2990.pokedexmyapplication2990

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashStart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_start)

        Handler(Looper.getMainLooper()).postDelayed({
            // Your Code
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)

    }
}