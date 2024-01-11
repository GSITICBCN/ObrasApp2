package com.obrasapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.obrasapp.data.DatosObra

class PresupuestoDatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presupuesto_datos)

        //Declaración de variables con asignación de los datos ingresados
        val fecha: EditText = findViewById(R.id.pres_fecha)
        val cliente: EditText = findViewById(R.id.pres_cliente)
        val nif: EditText = findViewById(R.id.pres_nif)
        val direccion: EditText = findViewById(R.id.pres_direccion)
        val descripcion: EditText = findViewById(R.id.pres_desc_mostrar)

        //Botón---------------------------------------------------------
        val btnSiguiente : Button = findViewById(R.id.pres_siguiente)

        //Al apretar el botón 'Siguiente'
        btnSiguiente.setOnClickListener{
            //Acceso a la base de datos
            val admin = BaseDatosApp(this, "bd", null, 1)
            val bd = admin.writableDatabase
            val reg = ContentValues()

            //Guardar datos de las obras ingresados
            reg.put("FECHA", fecha.text.toString())
            reg.put("CLIENTE", cliente.text.toString())
            reg.put("NIF", nif.text.toString())
            reg.put("DIRECCION", direccion.text.toString())
            reg.put("DESCRIPCION", descripcion.text.toString())
            bd.insert("DatosObra", null, reg)

            //Obtener número de presupuesto
            val fila = bd.rawQuery("SELECT NUM FROM DatosObra", null)
            var numPresupuesto = 0
            if(fila.moveToLast()) {
                numPresupuesto = fila.getString(0).toInt()
            }
            //Cerrar comunicación con base de datos
            bd.close()

            //Crear instancia de DatosObra
            val datosObra = DatosObra(
                numPresupuesto,
                fecha.text.toString(),
                cliente.text.toString(),
                nif.text.toString(),
                direccion.text.toString(),
                descripcion.text.toString())

            //Informar que se han agregado los datos y pasar valores a PresupuestoPartidas
            Toast.makeText(this, "¡Los datos se han agregado correctamente!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, PresupuestoPartidas::class.java)
            //Valores a pasar a PresupuestoPartidas
            intent.putExtra("datosObra", datosObra)
            startActivity(intent)

            //Vaciar campos formulario
            fecha.setText("")
            cliente.setText("")
            nif.setText("")
            direccion.setText("")
            descripcion.setText("")

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