package com.obrasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class Reforma : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reforma)

        //Botón---------------------------------------------------------
        val btnSiguiente : Button = findViewById(R.id.siguiente)
        //Índice acabados
        var indiceAcabados : Float = 1F
        //Spinner de acabados------------------------------------------------------
        val spinnerAcabados : Spinner = findViewById(R.id.spinner_acabados_ref)
        val acabados = arrayOf("Nivel de acabados","Bajo", "Medio", "Alto")
        val adaptadorAcabados = ArrayAdapter(this, android.R.layout.simple_spinner_item, acabados)
        spinnerAcabados.adapter = adaptadorAcabados

        btnSiguiente.setOnClickListener {
            //Superficie------------------------------------------------------
            val superficie: EditText = findViewById(R.id.ref_superficie)
            //Cantidad de dormitorios------------------------------------------------------
            val cantDormitorios: EditText = findViewById(R.id.ref_dormitorios)
            //Cantidad de baños------------------------------------------------------
            val cantBanos: EditText = findViewById(R.id.ref_banos)
            //Dependiendo del valor seleccionado es el índice por acabados
            when (spinnerAcabados.selectedItem.toString()) {
                "Bajo" -> indiceAcabados = 0.8F
                "Medio" -> indiceAcabados = 1.0F
                "Alto" -> indiceAcabados = 1.2F
            }
            if(superficie.text.toString().isBlank() ){
                Toast.makeText(this, "¡Debes ingresar la superficie!", Toast.LENGTH_SHORT).show()
            } else if(cantDormitorios.text.toString().isBlank()){
                Toast.makeText(this, "¡Debes ingresar la cantidad de dormitorios!", Toast.LENGTH_SHORT).show()
            }
            else if(cantBanos.text.toString().isBlank()){
                Toast.makeText(this, "¡Debes ingresar la cantidad de baños!", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, Reforma2::class.java)
                //Pasar valor a la otra actividad
                intent.putExtra("superficie", superficie.text.toString().toInt())
                intent.putExtra("dormitorios", cantDormitorios.text.toString().toInt())
                intent.putExtra("banos", cantBanos.text.toString().toInt())
                intent.putExtra("indiceAcabados", indiceAcabados)
                startActivity(intent)
            }
        }
    }
    fun toReforma2(view: View) {
        val intent = Intent (this, Reforma2::class.java).apply{}
        startActivity(intent)
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
