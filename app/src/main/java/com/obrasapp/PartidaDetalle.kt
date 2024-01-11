package com.obrasapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.obrasapp.data.DatosObra
import com.obrasapp.data.FilaPresupuesto
import com.obrasapp.data.Partida

@Suppress("DEPRECATION")
class PartidaDetalle : AppCompatActivity() {
    //Partidas globales
    lateinit var partidaElegida : Partida
    //Variable con los datos de la obra
    lateinit var datosObra: DatosObra

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partida_detalle)

        //Obtener los datos que pasó PartidasObra
        partidaElegida = intent.getParcelableExtra("partida")!!
        datosObra = intent.getParcelableExtra("datosObra")!!

        //Mostrar datos de partida
        val categoria : TextView = findViewById(R.id.partida_categoria_detalle)
        categoria.text = partidaElegida.categoria
        val id : TextView = findViewById(R.id.partida_id)
        id.text = partidaElegida.id.toString()
        val nombre : TextView = findViewById(R.id.partida_partida)
        nombre.text = partidaElegida.nombre
        val precio : TextView = findViewById(R.id.partida_precio_detalle)
        precio.text = partidaElegida.precio.toString()
        val unidades : TextView = findViewById(R.id.partida_unidades_detalle)
        unidades.text = partidaElegida.unidades

        //cantidad ingresada
        val cant: EditText = findViewById(R.id.partida_detalle_cantidad)

        //Acciones de botón para agregar la partida
        val btnAgregarPartida = findViewById<Button>(R.id.btn_agregar_a_pres) as Button
        btnAgregarPartida.setOnClickListener{
            if(cant.text.toString().isNotEmpty()){

                //calculo de subtotal
                val subTotal = cant.text.toString().toDouble() * precio.text.toString().toDouble()

                //Actualizar base de datos
                val admin = BaseDatosApp(this, "bd", null, 1)
                val bd = admin.writableDatabase
                val reg = ContentValues()
                reg.put("PARTIDA_ID", partidaElegida.id)
                reg.put("PRESUPUESTO_ID", datosObra.id)
                reg.put("CATEGORIA", partidaElegida.categoria)
                reg.put("NOMBRE", partidaElegida.nombre)
                reg.put("PRECIO", partidaElegida.precio)
                reg.put("UNIDADES", partidaElegida.unidades)
                reg.put("CANTIDAD", cant.text.toString().toDouble())
                reg.put("TOTAL", subTotal)

                bd.insert("PartidasPresupuesto", null, reg)
                bd.close()
                Toast.makeText(this, "La partida se ha agregado al presupuesto", Toast.LENGTH_SHORT).show()
                //ir a PartidasObra
                val intent = Intent(this, PartidasObra::class.java).apply {}
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "¡La cantidad debe ser mayor que 0!", Toast.LENGTH_SHORT).show()
            }
        }
        //Acciones de botón para quitar la partida
        val btnQuitarPartida = findViewById<Button>(R.id.btn_quitar) as Button
        btnQuitarPartida.setOnClickListener {
            //Acceso a la base de datos
            val admin = BaseDatosApp(this, "bd", null, 1)
            val bd = admin.writableDatabase
            val idPartida = partidaElegida.id
            //Quitar de registro
            bd.execSQL("DELETE FROM PartidasPresupuesto WHERE PARTIDA_ID = $idPartida")
            Toast.makeText(this, "La partida se ha quitado al presupuesto", Toast.LENGTH_SHORT).show()
            //ir a PartidasObra
            val intent = Intent(this, PartidasObra::class.java).apply {}
            startActivity(intent)
        }
    }
    fun toPartidas(view: View) {
        val intent = Intent (this, PresupuestoPartidas::class.java).apply{}
        startActivity(intent)
    }
    fun toMenu(view: View) {
        val intent = Intent (this, Menu::class.java).apply{}
        startActivity(intent)
    }
}