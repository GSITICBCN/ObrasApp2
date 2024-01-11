package com.obrasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Rapido : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rapido)
    }
    fun toObraNueva(view: View) {
        val intent = Intent (this, ObraNueva::class.java).apply{}
        startActivity(intent)
    }
    fun toMain(view: View) {
        val intent = Intent (this, MainActivity::class.java).apply{}
        startActivity(intent)
    }
    fun toReforma(view: View) {
        val intent = Intent (this, Reforma::class.java).apply{}
        startActivity(intent)
    }
    fun toPersonalizado(view: View) {
        val intent = Intent (this, Personalizado::class.java).apply{}
        startActivity(intent)
    }
    fun toMenu(view: View) {
        val intent = Intent (this, Menu::class.java).apply{}
        startActivity(intent)
    }
}