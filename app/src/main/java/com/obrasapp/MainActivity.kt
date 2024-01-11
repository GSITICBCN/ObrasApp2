package com.obrasapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun toQuick(view: View) {
        val intent = Intent (this, Rapido::class.java).apply{}
        startActivity(intent)
    }
    fun toPersonalizado(view: View) {

        //Captura de variable guardada en memoria
        val preferencias = getSharedPreferences("isLogged", Context.MODE_PRIVATE)
        val isLogged = preferencias.getString("Mensaje", "").toBoolean()
        val editor = preferencias.edit()
        editor.remove("Mensaje")
        editor.commit()

        if(isLogged){
            val intent = Intent (this, PresupuestoPersonalizado::class.java).apply{}
            startActivity(intent)
        }
        else{
            val intent = Intent (this, Personalizado::class.java).apply{}
            startActivity(intent)
        }
    }
    fun toArgs(view: View){
        val intent = Intent (this, WebArgs::class.java).apply{}
        startActivity(intent)
    }
}