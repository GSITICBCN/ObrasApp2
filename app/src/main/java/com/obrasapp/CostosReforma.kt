package com.obrasapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.text.DecimalFormat

class CostosReforma : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costos_reforma)

        //Obtener los datos que pasó Reforma
        val bundle = intent.extras
        var costosReforma = bundle?.getFloat("costoObraRef")

        //Dar formato con separador de miles
        val formatea = DecimalFormat("###,###.##")

        //Declaración de variables con asignación ed los TextView
        val montobras = findViewById<TextView>(R.id.ref_monto_obras)
        val arquitecto = findViewById<TextView>(R.id.ref_monto_arq)
        val impuestos = findViewById<TextView>(R.id.ref_monto_imp)
        val total = findViewById<TextView>(R.id.ref_monto_total)

        if (costosReforma != null) {
            val costoArq = costosReforma * 0.11
            val costoImp = costosReforma * 0.04

            //Asignación de valores a mostrar
            montobras.text = formatea.format(costosReforma.toInt()).toString()
            arquitecto.text = formatea.format(costoArq.toInt()).toString()
            impuestos.text = formatea.format(costoImp.toInt()).toString()
            total.text = formatea.format((costosReforma + costoArq + costoImp).toInt()).toString()

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