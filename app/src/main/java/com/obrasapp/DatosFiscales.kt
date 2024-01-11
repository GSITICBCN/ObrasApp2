package com.obrasapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DatosFiscales : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_fiscales)

        //Declaración de variables con asignación de los datos ingresados
        val razonSocial: EditText = findViewById(R.id.df_razon_social)
        val nif: EditText = findViewById(R.id.df_nif)
        val cp: EditText = findViewById(R.id.df_codigo_postal)
        val localidad: EditText = findViewById(R.id.df_localidad)
        val direccion: EditText = findViewById(R.id.df_direccion)
        val telefono: EditText = findViewById(R.id.df_telefono)
        val email: EditText = findViewById(R.id.df_email)
        val web: EditText = findViewById(R.id.df_web)

        //Botón---------------------------------------------------------
        val btnIngresarDatos : Button = findViewById(R.id.ingresar_datos)

        //Al apretar el botón
        btnIngresarDatos.setOnClickListener{
            val admin = BaseDatosApp(this, "bd", null, 1)
            val bd = admin.writableDatabase
            val reg = ContentValues()
            //Guardar datos ingresados
            reg.put("RSOCIAL", razonSocial.text.toString())
            reg.put("NIF", nif.text.toString())
            reg.put("CP", cp.text.toString())
            reg.put("LOCALIDAD", localidad.text.toString())
            reg.put("DIRECCION", direccion.text.toString())
            reg.put("TELEFONO", telefono.text.toString())
            reg.put("EMAIL", email.text.toString())
            reg.put("WEB", web.text.toString())

            bd.insert("DatosFiscales", null, reg)
            bd.close()

            razonSocial.setText("")
            nif.setText("")
            cp.setText("")
            localidad.setText("")
            direccion.setText("")
            telefono.setText("")
            email.setText("")
            web.setText("")

            Toast.makeText(this, "¡Los datos se han agregado correctamente!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, PresupuestoPersonalizado::class.java)
            startActivity(intent)

        }
    }
    fun toMain(view: View) {
        val intent = Intent (this, MainActivity::class.java).apply{}
        startActivity(intent)
    }
    fun toPresupuestoPersonalizado(view: View) {
        val intent = Intent (this, PresupuestoPersonalizado::class.java).apply{}
        startActivity(intent)
    }
    fun toMenu(view: View) {
        val intent = Intent (this, Menu::class.java).apply{}
        startActivity(intent)
    }
}