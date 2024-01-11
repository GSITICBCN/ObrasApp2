package com.obrasapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class GenerarPartida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generar_partida)

        val categoriaPartida : EditText = findViewById(R.id.partida_categoria)
        val nombrePartida : EditText = findViewById(R.id.partida_nombre)
        val precioPartida : EditText = findViewById(R.id.partida_precio)
        val unidadesPartida : EditText = findViewById(R.id.partida_unidades)
        val btnGenerarPartida : Button = findViewById(R.id.btn_generar_partida)

        btnGenerarPartida.setOnClickListener{
            val admin = BaseDatosApp(this, "bd", null, 1)
            val bd = admin.writableDatabase
            val reg = ContentValues()

            reg.put("CATEGORIA", categoriaPartida.text.toString())
            reg.put("NOMBRE", nombrePartida.text.toString())
            reg.put("PRECIO", precioPartida.text.toString().toDouble())
            reg.put("UNIDADES", unidadesPartida.text.toString())

            bd.insert("Partidas", null, reg)
            bd.close()

            categoriaPartida.setText("")
            nombrePartida.setText("")
            precioPartida.setText("")
            unidadesPartida.setText("")

            Toast.makeText(this, "¡Los datos se han agregado correctamente!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, PartidasObra::class.java)
            startActivity(intent)
        }

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