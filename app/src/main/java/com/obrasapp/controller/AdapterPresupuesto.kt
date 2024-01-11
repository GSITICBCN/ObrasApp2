package com.obrasapp.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.obrasapp.R
import com.obrasapp.data.Presupuesto

class AdapterPresupuesto(private val presupuestosList: ArrayList<Presupuesto>): RecyclerView.Adapter<AdapterPresupuesto.ViewHolder>(){

    lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_presupuestos_list, parent, false)
        return  ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val presupuesto = presupuestosList[position]

        holder.presupuestoNumero = presupuesto.numeroPresupuesto

    }

    override fun getItemCount(): Int {
        return presupuestosList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var presupuestoNumero: Int = 0
        lateinit var presupuestoNombre : String
        var presupuestoUser : Int = 0
    }

}