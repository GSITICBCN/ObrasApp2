package com.obrasapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.obrasapp.controller.AdapterPartidas
import com.obrasapp.data.DatosObra
import com.obrasapp.data.FilaPresupuesto
import com.obrasapp.data.Partida

@Suppress("DEPRECATION")
class PartidasObra : AppCompatActivity() {

    //Elemento al que se le asignarán los item_partida
    private lateinit var adapterPartidas: AdapterPartidas
    private lateinit var recycledView: RecyclerView
    //Variable para almacenar el total por partida
    private var total: Double = 0.0
    //Variable para guardar los datos de la obra
    lateinit var datosObra: DatosObra

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partidas_obra)

        //Obtener los datos que pasó PresupuestoPartidas
        datosObra = intent.getParcelableExtra("datosObra")!!
    }
    //Generar la vista después de cargar la lista de partidas
    override fun onStart() {
        super.onStart()
        val layoutManager = LinearLayoutManager(this)
        recycledView = findViewById(R.id.rv_lista_partidas)
        recycledView.layoutManager = layoutManager
        recycledView.setHasFixedSize(true)
        adapterPartidas = AdapterPartidas(getPartidasList())
        recycledView.adapter = adapterPartidas

        //Acciones al seleccionar una partida
        adapterPartidas.onItemClick = {
            val intent = Intent (this, PartidaDetalle::class.java).apply{}
            intent.putExtra("partida", it)
            intent.putExtra("datosObra", datosObra)
            startActivity(intent)
        }
    }
    fun getPartidasList() : ArrayList<Partida>{

        //Array para almacenar las partidas
        val partidasList : ArrayList<Partida> = ArrayList()

        //Acceso a la base de datos
        val admin = BaseDatosApp(this, "bd", null, 1)
        val bd = admin.writableDatabase

        //Obtención de registros
        val reg = bd.rawQuery("SELECT ID, CATEGORIA, NOMBRE, PRECIO, UNIDADES FROM Partidas", null)

        //Declaración de variables para guardar los datos de la BD
        var id : Int
        var categoria : String
        var nombre : String
        var precio : Double
        var unidades : String

        //Recorrer la BD y asignar cada registro a una variable
        if (reg.moveToFirst()){
            do {
                id = reg.getString(0).toInt()
                categoria = reg.getString(1)
                nombre = reg.getString(2)
                precio = reg.getString(3).toDouble()
                unidades = reg.getString(4)
                //Agregar variables con valores de la BD al Array que guarda las partidas
                partidasList.add(Partida(id, categoria, nombre, precio, unidades))
            } while (reg.moveToNext())
        }
        reg.close()
        return partidasList
    }
    fun toMain(view: View) {
        val intent = Intent (this, MainActivity::class.java).apply{}
        startActivity(intent)
    }
    fun toGenerarPartida(view: View) {
        val intent = Intent (this, GenerarPartida::class.java).apply{}
        startActivity(intent)
    }
    fun toMenu(view: View) {
        val intent = Intent (this, Menu::class.java).apply{}
        startActivity(intent)
    }
    fun toPresupuestoPartidas(view: View) {
        //ir a PresupuestoPartidas y pasar fila de presupuesto
        val intent = Intent (this, PresupuestoPartidas::class.java).apply{}
        //Valores a pasar a PresupuestoPartidas
        intent.putExtra("datosObra", datosObra)
        startActivity(intent)
    }
}
