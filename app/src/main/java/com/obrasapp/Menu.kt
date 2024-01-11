package com.obrasapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //Captura de variable guardada en memoria
        val preferencias = getSharedPreferences("isLogged", Context.MODE_PRIVATE)
        val isLogged = preferencias.getString("Mensaje", "").toBoolean()
        val editor = preferencias.edit()
        editor.commit()


        //Lista del menú
        val arrayAdapter: ArrayAdapter<String>
        val itemsMenu = arrayOf("Inicio", "Presupuesto rápido", "Obra nueva", "Reforma", "Agregar datos fiscales", "Presupuestos", "Política de privacidad", "Web args")
        val listaMenu = findViewById<ListView>(R.id.lista_menu)

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsMenu)
        listaMenu.adapter = arrayAdapter

        listaMenu.setOnItemClickListener { parent, view, position, id ->
            val element = arrayAdapter.getItem(position)
            when (element) {
                "Inicio" -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                "Presupuesto rápido" -> {
                    val intent = Intent(this, Rapido::class.java)
                    startActivity(intent)
                }
                "Obra nueva"-> {
                    val intent = Intent(this, ObraNueva::class.java)
                    startActivity(intent)
                }
                "Reforma"-> {
                    val intent = Intent(this, Reforma::class.java)
                    startActivity(intent)
                }
                "Agregar datos fiscales"-> {
                    if(isLogged){
                        val intent = Intent (this, DatosFiscales::class.java).apply{}
                        startActivity(intent)
                    }
                    else{
                        val intent = Intent (this, IniciarSesion::class.java).apply{}
                        startActivity(intent)
                    }
                }
                "Presupuestos"-> {
                    if(isLogged){
                        val intent = Intent (this, ListadoPresupuestos::class.java).apply{}
                        startActivity(intent)
                    }
                    else{
                        val intent = Intent (this, IniciarSesion::class.java).apply{}
                        startActivity(intent)
                    }
                }
                "Política de privacidad"-> {
                    val intent = Intent (this, Privacidad::class.java).apply{}
                    startActivity(intent)
                }
                "Web args"-> {
                    val intent = Intent(this, WebArgs::class.java)
                    startActivity(intent)
                }
            }
        }
    }
    fun close(view: View) {
        onBackPressed()
    }
}