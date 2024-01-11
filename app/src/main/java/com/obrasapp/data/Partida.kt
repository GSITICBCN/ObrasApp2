package com.obrasapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Partida(
    val id: Int,
    val categoria: String,
    val nombre: String,
    val precio: Double,
    val unidades: String
) : Parcelable