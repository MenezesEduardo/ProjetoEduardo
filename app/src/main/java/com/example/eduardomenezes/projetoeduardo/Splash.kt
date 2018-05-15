package com.example.eduardomenezes.projetoeduardo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.schedule


class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        supportActionBar?.hide()

    }

    override fun onStart() {
        super.onStart()
        Timer("SettingUp", false).schedule(2000) {
            val intent = Intent(this@Splash, Login::class.java)
            startActivity(intent)
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}