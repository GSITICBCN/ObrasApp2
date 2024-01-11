package com.obrasapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.obrasapp.controller.AdapterPartidasPresupuesto
import com.obrasapp.data.DatosObra
import com.obrasapp.data.FilaPresupuesto
import com.obrasapp.data.Partida
import com.obrasapp.data.Presupuesto

@Suppress("DEPRECATION")
class PresupuestoPartidas : AppCompatActivity() {

    //variables globales
    lateinit var datosObra: DatosObra
    var numPres: Int = 0
    var nombrePresupuesto: String = ""
    var totalPresupuesto: Double = 0.0
    //Array para almacenar las partidas
    var partidasPresupuesto : ArrayList<FilaPresupuesto> = ArrayList()

    //Elemento al que se le asignarán los item_partida
    private lateinit var adapterPartidasPresupuesto: AdapterPartidasPresupuesto
    private lateinit var recycledView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presupuesto_partidas)

        //Obtener los datos que le pasaron a esta actividad
        datosObra = intent.getParcelableExtra("datosObra")!!

        //Mostrar título del presupuesto
        val direccion: TextView = findViewById(R.id.presupuesto_nombre)
        direccion.text = datosObra.direccion
        nombrePresupuesto = direccion.text.toString()
        //Mostrar fecha del presupuesto
        val fecha: TextView = findViewById(R.id.titulo_fecha)
        fecha.text = datosObra.fecha
        //Mostrar cliente
        val cliente: TextView = findViewById(R.id.pres_cliente_nombre)
        cliente.text = datosObra.cliente
        //Mostrar NIF
        val nif: TextView = findViewById(R.id.pres_cliente_nif)
        nif.text = datosObra.nif
        //Mostrar descripción del presupuesto
        val descripcion: TextView = findViewById(R.id.pres_obras)
        descripcion.text = datosObra.descripcion
        //Mostrar número de presupuesto
        val numPresupuesto: TextView = findViewById(R.id.titulo_num_pres)
        numPresupuesto.text = datosObra.id.toString()
        numPres = numPresupuesto.text.toString().toInt()

        val btnGuardar = findViewById<Button>(R.id.pres_guardar)
        btnGuardar.setOnClickListener{
            generarPresupuesto()
        }
    }
    override fun onStart() {
        super.onStart()
        val layoutManager = LinearLayoutManager(this)
        //Mostrar la lista de partidas seleccionadas
        recycledView = findViewById(R.id.rv_lista_partidas_presupuesto)
        recycledView.layoutManager = layoutManager
        recycledView.setHasFixedSize(true)
        adapterPartidasPresupuesto = AdapterPartidasPresupuesto(getPartidasPresupuestoList())
        recycledView.adapter = adapterPartidasPresupuesto

         adapterPartidasPresupuesto.onItemClick = {
             val intent = Intent (this, PartidaDetalle::class.java).apply{}
             //Valores a pasar a PartidaDetalle
             intent.putExtra("partida", it.partida)
             intent.putExtra("datosObra", datosObra)
             startActivity(intent)
           }
    }

    fun getPartidasPresupuestoList(): ArrayList<FilaPresupuesto> {

        //Acceso a la base de datos
        val admin = BaseDatosApp(this, "bd", null, 1)
        val bd = admin.writableDatabase

        //Obtención de registros
        val reg = bd.rawQuery("SELECT ID, PARTIDA_ID, CATEGORIA, NOMBRE, PRECIO, UNIDADES, CANTIDAD, TOTAL FROM PartidasPresupuesto WHERE PRESUPUESTO_ID = '$numPres'", null)

        //Declaración de variables para guardar los datos de la BD
        var id: Int
        var partidaId: Int
        var categoria: String
        var nombre: String
        var precio: Double
        var unidades: String
        var cantidad : Double
        var total: Double

        //Recorrer la BD y asignar cada registro a una variable
        if (reg.moveToFirst()){
            do {
                id = reg.getString(0).toInt()
                partidaId = reg.getString(1).toInt()
                categoria = reg.getString(2)
                nombre = reg.getString(3)
                precio = reg.getString(4).toDouble()
                unidades = reg.getString(5)
                cantidad = reg.getString(6).toDouble()
                total = reg.getString(7).toDouble()

                //Mostrar total del presupuesto
                val totalPres: TextView = findViewById(R.id.total_valor)
                totalPresupuesto += total
                totalPres.text = totalPresupuesto.toString()

                //Agregar variables con valores de la BD a la lista de presupuestos
                partidasPresupuesto.add(FilaPresupuesto(Partida(partidaId, categoria, nombre, precio, unidades), cantidad, total))
            } while (reg.moveToNext())
        }
        else{
            Toast.makeText(this, "Todavía no se han agregado partidas", Toast.LENGTH_SHORT).show()
        }
        reg.close()
        return partidasPresupuesto
    }

    fun toMain(view: View) {
        val intent = Intent (this, MainActivity::class.java).apply{}
        startActivity(intent)
    }

    fun toPartidasObra(view: View) {
        val intent = Intent (this, PartidasObra::class.java).apply{}
        //Valores a pasar a PartidasObra
        intent.putExtra("datosObra", datosObra)
        startActivity(intent)
    }

    fun toMenu(view: View) {
        val intent = Intent (this, Menu::class.java).apply{}
        startActivity(intent)
    }

    fun generarPresupuesto() {

        //Actualizar base de datos
        val admin = BaseDatosApp(this, "bd", null, 1)
        val bd = admin.writableDatabase
        val reg = ContentValues()

        reg.put("ID", numPres)
        reg.put("NOMBRE", nombrePresupuesto)

        bd.insert("ListaPresupuestos", null, reg)
        bd.close()
        //ir a PresupuestoPartidas
        val intent = Intent (this, ListadoPresupuestos::class.java).apply{}
        startActivity(intent)
    }
}