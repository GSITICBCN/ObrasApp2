package com.obrasapp.data

data class PartidasPresupuesto(
    val id: Int,
    val partidaId: Int,
    val presupuestoId: Int,
    val categoria: String,
    val nombre: String,
    val precio:Double,
    val unidades: String,
    val cantidad: Double,
    val total: Double
)
