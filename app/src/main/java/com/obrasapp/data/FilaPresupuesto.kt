package com.obrasapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilaPresupuesto (
    var partida: Partida,
    var cantidad: Double,
    var total: Double
    ) : Parcelable