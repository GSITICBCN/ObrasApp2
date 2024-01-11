package com.obrasapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.text.DecimalFormat
import android.graphics.*
import androidx.core.content.ContextCompat
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfPTable
import com.obrasapp.data.LineaPresupuesto
import java.io.FileNotFoundException


class Costos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costos)

        //Obtener costo pasado
        val bundle = intent.extras
        val costo = bundle?.getFloat("costoNueva")

        //Dar formato con separador de miles
        val formatea = DecimalFormat("###,###.##")

        //Declaración de variables con asignación ed los TextView
        val montobras = findViewById<TextView>(R.id.ref_monto_obras)
        val arquitecto = findViewById<TextView>(R.id.ref_monto_arq)
        val ingeniero = findViewById<TextView>(R.id.monto_ingeniero)
        val topografo = findViewById<TextView>(R.id.monto_topo)
        val impuestos = findViewById<TextView>(R.id.ref_monto_imp)
        val escrituras = findViewById<TextView>(R.id.monto_escrituras)
        val total = findViewById<TextView>(R.id.ref_monto_total)

        if (costo != null) {
            val costoArq = costo * 0.11
            val costoIng = costo * 0.05
            val costoTopo = 1500.00
            val costoImp = costo * 0.07
            val costoEsc = costo *0.05

            //Asignación de valores a las variables
            montobras.text = formatea.format(costo.toInt()).toString()
            arquitecto.text = formatea.format(costoArq.toInt()).toString()
            ingeniero.text = formatea.format(costoIng.toInt()).toString()
            topografo.text = formatea.format(costoTopo.toInt()).toString()
            impuestos.text = formatea.format(costoImp.toInt()).toString()
            escrituras.text = formatea.format(costoEsc.toInt()).toString()
            total.text = formatea.format((costo + costoArq + costoIng + costoTopo + costoImp + costoEsc).toInt()).toString()
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