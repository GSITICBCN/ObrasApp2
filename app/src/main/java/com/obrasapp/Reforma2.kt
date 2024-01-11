package com.obrasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox

class Reforma2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reforma2)

        //Obtener los datos que pasó Reforma
        val bundle = intent.extras
        val superficie = bundle?.getInt("superficie")
        val dormitorios = bundle?.getInt("dormitorios")
        val banos = bundle?.getInt("banos")
        val indiceAcabados = bundle?.getFloat("indiceAcabados")

        //Obtener datos de los check boxes
        val cocina : CheckBox = findViewById(R.id.ref_cocina)
        val distribucion : CheckBox = findViewById(R.id.ref_distribucion)
        val fontaneria : CheckBox = findViewById(R.id.ref_fontaneria)
        val electrica : CheckBox = findViewById(R.id.ref_electrica)
        val calefaccion : CheckBox = findViewById(R.id.ref_calefaccion)
        val puertas : CheckBox = findViewById(R.id.ref_puertas)
        val pintura : CheckBox = findViewById(R.id.ref_pintura)

        //Variable que contiene la incidencia por metro cuadrado
        var incidencia : Float = 250.0f

        //Variable que contendrá el resultado de lo seleccionado
        var resultado : Float = 0.0f

        //Variable que contendrá el resultado de lo seleccionado
        var costoObraRef : Float = 0.0f

        val btnRefCalcular : Button = findViewById(R.id.btn_ref_calcular)
        btnRefCalcular.setOnClickListener{
            if (cocina.isChecked){
                resultado += 9000
            }
            if (distribucion.isChecked){
                incidencia += 100
            }
            if (fontaneria.isChecked){
                resultado += 1500
            }
            if (electrica.isChecked){
                resultado += 1700
            }
            if (calefaccion.isChecked){
                resultado += 1700
            }
            if (puertas.isChecked){
                resultado += 180*(2+dormitorios!!+banos!!)
            }
            if (pintura.isChecked){
                incidencia += 9
            }
            //Calcular costo obras
            costoObraRef = (superficie!! * incidencia + resultado) * indiceAcabados!!
            //Abrir actividad que muestra el detalle de costos
            val intent = Intent (this, CostosReforma::class.java)
            //Pasar valor a la otra actividad
            intent.putExtra("costoObraRef", costoObraRef)
            startActivity(intent)
        }

    }
    fun toMain(view: View) {
        val intent = Intent (this, MainActivity::class.java).apply{}
        startActivity(intent)
    }
    fun toReforma(view: View) {
        val intent = Intent (this, Reforma::class.java).apply{}
        startActivity(intent)
    }
    fun toMenu(view: View) {
        val intent = Intent (this, Menu::class.java).apply{}
        startActivity(intent)
    }
}