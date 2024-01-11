package com.obrasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.obrasapp.data.DatosObra


class ListadoPresupuestos : AppCompatActivity() {
    var numPres: Int = 0
    lateinit var datosObra: DatosObra
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Configuración de la ListView
        setContentView(R.layout.activity_listado_presupuestos)
        val presupuestosListView = findViewById<ListView>(R.id.lv_presupuestos)
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, getPresupuestosList())
        presupuestosListView.adapter = arrayAdapter
        presupuestosListView.setOnItemClickListener{parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as String
            numPres = getNumPresupuesto(selectedItem)
            datosObra = getDatosObra(numPres)
            val intent = Intent (this, PresupuestoPartidas::class.java).apply{}
            //Valores a pasar a PresupuestoPartidas
            intent.putExtra("datosObra", datosObra)
            startActivity(intent)
        }
    }

    fun toMain(view: View) {
        val intent = Intent (this, MainActivity::class.java).apply{}
        startActivity(intent)
    }

    fun toPresupuestoDatos(view: View) {
        val intent = Intent (this, PresupuestoDatos::class.java).apply{}
        startActivity(intent)
    }
    fun toMenu(view: View) {
        val intent = Intent (this, Menu::class.java).apply{}
        startActivity(intent)
    }
    fun getPresupuestosList() : ArrayList<String>{

        //Array para almacenar las partidas
        val listaPresupuestos : ArrayList<String> = ArrayList()

        //Acceso a la base de datos
        val admin = BaseDatosApp(this, "bd", null, 1)
        val bd = admin.writableDatabase

        //Obtención de registros
        val reg = bd.rawQuery("SELECT ID, NOMBRE FROM ListaPresupuestos", null)

        //Declaración de variables para guardar los datos de la BD
        var id : Int
        var nombre : String

        //Recorrer la BD y asignar cada registro a una variable
        if (reg.moveToFirst()){
            do {
                id = reg.getString(0).toInt()
                nombre = reg.getString(1)
                //Agregar variables con valores de la BD a la lista de presupuestos
                listaPresupuestos.add( "$id: $nombre")
            } while (reg.moveToNext())
        }
        reg.close()
        return listaPresupuestos
    }
    fun getDatosObra(id: Int) : DatosObra{

        //Acceso a la base de datos
        val admin = BaseDatosApp(this, "bd", null, 1)
        val bd = admin.writableDatabase

        //Obtención de registros
        val reg = bd.rawQuery("SELECT NUM, FECHA, CLIENTE, NIF, DIRECCION, DESCRIPCION FROM DatosObra WHERE NUM = '$id'", null)

        //Declaración de variables
        var num: Int
        var fecha: String
        var cliente: String
        var nif: String
        var direccion: String
        var descripcion: String

        //Recorrer la BD y asignar cada registro a una variable
        if (reg.moveToFirst()){
                num = reg.getString(0).toInt()
                fecha = reg.getString(1)
                cliente = reg.getString(2)
                nif = reg.getString(3)
                direccion = reg.getString(4)
                descripcion = reg.getString(5)
            //Crear instancia de DatosObra
            datosObra = DatosObra(num, fecha, cliente, nif, direccion, descripcion)
        }
        reg.close()

        return datosObra
    }
    fun getNumPresupuesto(nomPres: String) : Int{
        var numPresupuesto: Int = 0
        var indice = 0
        val dosPuntos: Char = ':'
        var textoNumPres: String = ""
        while (nomPres[indice].compareTo(dosPuntos) != 0){
            textoNumPres += nomPres[indice]
            indice++
        }
        numPresupuesto = textoNumPres.toInt()
        return numPresupuesto
    }
}
