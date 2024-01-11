package com.obrasapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Presupuesto(
    var numeroPresupuesto: Int,
    var datosObra: DatosObra,
    var listaPartidas: ArrayList<FilaPresupuesto>
) : Parcelable