package com.obrasapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatosApp (context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, name, factory, version){

    private val tablaUsuarios = "CREATE TABLE Usuarios" +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "EMAIL TEXT," +
            "PASSWORD TEXT)"

    val tablaDatosFiscales = "CREATE TABLE DatosFiscales" +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "RSOCIAL TEXT," +
            "NIF TEXT," +
            "CP INTEGER," +
            "LOCALIDAD TEXT," +
            "DIRECCION TEXT," +
            "TELEFONO TEXT," +
            "EMAIL TEXT," +
            "WEB TEXT," +
            "LOGO INTEGER," +
            "USER INTEGER)"

    val tablaDatosObra = "CREATE TABLE DatosObra" +
            "(NUM INTEGER PRIMARY KEY AUTOINCREMENT," +
            "FECHA TEXT," +
            "CLIENTE TEXT," +
            "NIF TEXT," +
            "DIRECCION TEXT," +
            "DESCRIPCION TEXT)"

    val tablaPartidas = "CREATE TABLE Partidas" +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "CATEGORIA TEXT," +
            "NOMBRE TEXT," +
            "PRECIO REAL," +
            "UNIDADES TEXT)"

    val tablaListaPresupuestos = "CREATE TABLE ListaPresupuestos" +
            "(ID INTEGER PRIMARY KEY," +
            "NOMBRE TEXT)"

    val tablaPartidasPresupuesto = "CREATE TABLE PartidasPresupuesto" +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "PARTIDA_ID INTEGER," +
            "PRESUPUESTO_ID INTEGER," +
            "CATEGORIA TEXT," +
            "NOMBRE TEXT," +
            "PRECIO REAL," +
            "UNIDADES TEXT," +
            "CANTIDAD REAL," +
            "TOTAL REAL)"

    override fun onCreate(database: SQLiteDatabase?) {
        database?.execSQL(tablaUsuarios)
        database?.execSQL(tablaDatosFiscales)
        database?.execSQL(tablaDatosObra)
        database?.execSQL(tablaPartidas)
        database?.execSQL(partidasPrinciales)
        database?.execSQL(tablaListaPresupuestos)
        database?.execSQL(tablaPartidasPresupuesto)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    val partidasPrinciales = "INSERT INTO Partidas (ID, CATEGORIA, NOMBRE, PRECIO, UNIDADES) VALUES" +
            "(1, 'Alicatados', 'Azulejo rojo 20 x 20', 20.50, 'm²')," +
            "(2, 'Alicatados', 'Azulejo rojo 30 x 30', 15.00, 'm²')," +
            "(3, 'Alicatados', 'Porcelánico 60 x 30', 23.00, 'm²');"

}
