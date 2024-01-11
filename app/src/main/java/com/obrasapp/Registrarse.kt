package com.obrasapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        val email = findViewById<EditText>(R.id.et_user_email_add)
        val pass1 = findViewById<EditText>(R.id.et_pass_1_add)
        val pass2 = findViewById<EditText>(R.id.et_pass_2_add)
        val btnAgregarUsuario = findViewById<Button>(R.id.btn_agregar_usuario)

        btnAgregarUsuario.setOnClickListener{
            if(pass1.text.toString() == pass2.text.toString()) {
                Toast.makeText(
                    this,
                    "¡El usuario se ha agregado correctamente!",
                    Toast.LENGTH_SHORT
                ).show()
                val admin = BaseDatosApp(this, "bd", null, 1)
                val bd = admin.writableDatabase
                val reg = ContentValues()

                reg.put("EMAIL", email.text.toString())
                reg.put("PASSWORD", pass1.text.toString())

                bd.insert("Usuarios", null, reg)
                bd.close()

                email.setText("")
                pass1.setText("")
                pass2.setText("")

                val intent = Intent(this, IniciarSesion::class.java)
                startActivity(intent)
            }
            else{Toast.makeText(
                this,
                "¡Las contraseñas no coinciden!",
                Toast.LENGTH_SHORT
            ).show()}
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