package com.obrasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PresupuestoPersonalizado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presupuesto_personalizado)
    }
    fun toMain(view: View) {
        val intent = Intent (this, MainActivity::class.java).apply{}
        startActivity(intent)
    }
    fun toPartidas(view: View) {
        val intent = Intent (this, PartidasObra::class.java).apply{}
        startActivity(intent)
    }
    fun toListaPresupuestos(view: View) {
        val intent = Intent (this, ListadoPresupuestos::class.java).apply{}
        startActivity(intent)
    }
    fun toDatosFiscales(view: View) {
        val intent = Intent (this, DatosFiscales::class.java).apply{}
        startActivity(intent)
    }
    fun toMenu(view: View) {
        val intent = Intent (this, Menu::class.java).apply{}
        startActivity(intent)
    }
}