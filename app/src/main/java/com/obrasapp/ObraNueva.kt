package com.obrasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class ObraNueva : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_obra_nueva)

        //Índices iniciales
        var indiceAcabados : Float = 1F
        var indiceTipologia : Float = 1F
        var costoObraNueva : Float = 0F

        //Spinner de tipología------------------------------------------------------
        val spinnerTipologia : Spinner = findViewById(R.id.spinner_tipologia)
        val tipologias = arrayOf("Tipología","Aislada", "Pareada", "Entre medianeras")
        val adaptadorTipologias = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipologias)
        spinnerTipologia.adapter = adaptadorTipologias

        //Spinner de acabados------------------------------------------------------
        val spinnerAcabados : Spinner = findViewById(R.id.spinner_acabados)
        val acabados = arrayOf("Nivel de acabados","Bajo", "Medio", "Alto")
        val adaptadorAcabados = ArrayAdapter(this, android.R.layout.simple_spinner_item, acabados)
        spinnerAcabados.adapter = adaptadorAcabados

        //Botón---------------------------------------------------------
        val btnCalcular : Button = findViewById(R.id.calcular)

        btnCalcular.setOnClickListener{
            //Dependiendo del valor seleccionado es el índice por tipologia
            when(spinnerTipologia.selectedItem.toString()){
                "Tipología"-> indiceTipologia = 1.0F
                "Aislada" -> indiceTipologia = 1.15F
                "Pareada" -> indiceTipologia = 1.05F
                "Entre medianeras" -> indiceTipologia = 1.0F
            }
            //Dependiendo del valor seleccionado es el índice por acabados
            when(spinnerTipologia.selectedItem.toString()){
                "Bajo" -> indiceAcabados = 0.8F
                "Medio" -> indiceAcabados = 1.0F
                "Alto" -> indiceAcabados = 1.2F
            }
            //Superficie------------------------------------------------------
            val valorSuperficie: EditText = findViewById(R.id.superficie)
            //Cantidad de baños------------------------------------------------------
            val cantidadBanos: EditText = findViewById(R.id.banos)
            //Cálculos, si se ingresó superficie y cantidad de baños------------------------------------------------------
            if(valorSuperficie.text.toString().isBlank() || cantidadBanos.text.toString().isBlank()){
                Toast.makeText(this, "¡Debes ingresar al menos una superficie y una cantidad de baños!", Toast.LENGTH_SHORT).show()
            }
            else {
                val mbeInicial = 757.0F
                val mbeActual = 757.0F
                val actualizacionMBE = mbeActual / mbeInicial
                val costoBano = 4500.0F
                costoObraNueva =
                    (mbeInicial * indiceTipologia * indiceAcabados * valorSuperficie.text.toString()
                        .toInt() + cantidadBanos.text.toString()
                        .toInt() * costoBano) * actualizacionMBE * 1.21F

                //Abrir actividad que muestra el detalle de costos
                val intent = Intent(this, Costos::class.java)
                //Pasar valor a la otra actividad
                intent.putExtra("costoNueva", costoObraNueva)
                startActivity(intent)
            }
        }
    }
    fun toMain(view: View) {
        val intent = Intent (this, MainActivity::class.java).apply{}
        startActivity(intent)
    }
    fun toQuick(view: View) {
        val intent = Intent (this, Rapido::class.java).apply{}
        startActivity(intent)
    }
    fun toMenu(view: View) {
        val intent = Intent (this, Menu::class.java).apply{}
        startActivity(intent)
    }
}
