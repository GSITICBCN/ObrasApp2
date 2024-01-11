package com.obrasapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class IniciarSesion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)
    }
    fun toMain(view: View) {
        val intent = Intent (this, MainActivity::class.java).apply{}
        startActivity(intent)
    }
    fun toRegistrarse(view: View) {
        val intent = Intent (this, Registrarse::class.java).apply{}
        startActivity(intent)
    }
    fun toPresupuestoPersonalizado(view: View) {
        val intent = Intent (this, PresupuestoPersonalizado::class.java).apply{}
        startActivity(intent)
    }
    fun toInicioSesion(view: View) {
        val etEmail: EditText = findViewById(R.id.et_email)
        val etPass: EditText = findViewById(R.id.et_pass)
        var sesionIniciada: Boolean

        val admin = BaseDatosApp(this, "bd", null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("SELECT EMAIL, PASSWORD FROM Usuarios WHERE EMAIL='${etEmail.text.toString()}' AND PASSWORD='${etPass.text.toString()}'", null)
        var email = ""
        var pass = ""
        if (fila.moveToFirst()){
            email = fila.getString(0)
            pass = fila.getString(1)
        }
        if(etEmail.text.toString()!= "" && etPass.text.toString() != ""){
            if(etEmail.text.toString() == email){
                if(etPass.text.toString() == pass){
                    sesionIniciada = true

                    //Guardar variable en memoria
                    val preferencias = getSharedPreferences("isLogged", Context.MODE_PRIVATE)
                    val editor = preferencias.edit()
                    editor.putString("Mensaje", sesionIniciada.toString())
                    editor.commit()

                    //Ir a otra actividad
                    val intent = Intent(this, PresupuestoPersonalizado::class.java)
                    startActivity(intent)
                }
            }
            else{
                Toast.makeText(this, "El usuario o la contraseña son incorrectos.", Toast.LENGTH_SHORT).show()
            }
        }
        else {Toast.makeText(this, "Debes ingresar un e-mail y una contraseña.", Toast.LENGTH_SHORT).show()}
    }
    fun toMenu(view: View) {
        val intent = Intent (this, Menu::class.java).apply{}
        startActivity(intent)
    }
}