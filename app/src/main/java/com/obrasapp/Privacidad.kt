package com.obrasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Privacidad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacidad)
    }
    fun toMain(view: View) {
        val intent = Intent (this, MainActivity::class.java).apply{}
        startActivity(intent)
    }
    fun toMenu(view: View) {
        val intent = Intent (this, Menu::class.java).apply{}
        startActivity(intent)
    }
}