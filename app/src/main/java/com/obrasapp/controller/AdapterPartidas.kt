package com.obrasapp.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.obrasapp.R
import com.obrasapp.data.Partida

class AdapterPartidas (private val partidasList: ArrayList<Partida>): RecyclerView.Adapter<AdapterPartidas.ViewHolder>(){

    lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_partidas_obra, parent, false)
        return  ViewHolder(itemView)
    }
    var  onItemClick : ((Partida) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var partidaId: Int = 0
        var partidaCategoria: String = ""
        var partidaNombre : TextView = itemView.findViewById(R.id.item_partida_descripcion)
        var partidaPrecio: Double = 0.0
        var partidaUnidades: String = ""
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val partida = partidasList[position]

        holder.partidaId = partida.id
        holder.partidaCategoria = partida.categoria
        holder.partidaNombre.text = partida.nombre
        holder.partidaPrecio = partida.precio
        holder.partidaUnidades = partida.unidades

        //Al seleccionar una partida de la lista se invoca a la partida
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(partida)
        }

    }

    override fun getItemCount(): Int {
        return partidasList.size
    }

}