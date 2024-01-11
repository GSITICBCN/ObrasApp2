package com.obrasapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DatosObra(
    val id: Int,
    val fecha: String,
    val cliente: String,
    val nif: String,
    val direccion: String,
    val descripcion: String
    ) : Parcelable