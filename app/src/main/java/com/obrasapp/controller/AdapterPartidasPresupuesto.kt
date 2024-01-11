package com.obrasapp.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.obrasapp.R
import com.obrasapp.data.FilaPresupuesto

class AdapterPartidasPresupuesto (private val partidasPresupuestoList: ArrayList<FilaPresupuesto>): RecyclerView.Adapter<AdapterPartidasPresupuesto.ViewHolder>(){

    lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_partidas_presupuesto, parent, false)
        return  ViewHolder(itemView)
    }
    var  onItemClick : ((FilaPresupuesto) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nombre: TextView = itemView.findViewById(R.id.partida_elegida_partida)
        var cantidad:  TextView = itemView.findViewById(R.id.partida_elegida_cantidad)
        var unidades : TextView = itemView.findViewById(R.id.partida_elegida_unidad)
        var precio:  TextView = itemView.findViewById(R.id.partida_elegida_unitario)
        var total :  TextView = itemView.findViewById(R.id.partida_elegida_total)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val filaPresupuesto = partidasPresupuestoList[position]

        holder.nombre.text = filaPresupuesto.partida.nombre
        holder.cantidad.text = filaPresupuesto.cantidad.toString()
        holder.unidades.text = filaPresupuesto.partida.unidades
        holder.precio.text = filaPresupuesto.partida.precio.toString()
        holder.total.text = filaPresupuesto.total.toString()

       //Al seleccionar una fila de presupuesto se invoca a filaPresupuesto
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(filaPresupuesto)
        }

    }
    override fun getItemCount(): Int {
        return partidasPresupuestoList.size
    }
}