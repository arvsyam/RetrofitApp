package com.adl.retrofitapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adl.retrofitapp.R
import com.adl.retrofitapp.model.AbsenItem

class AbsenAdapter(var data: ArrayList<AbsenItem?>): RecyclerView.Adapter<AbsenViewHolder>() {
    lateinit var parent:ViewGroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsenViewHolder {
        this.parent = parent

        return AbsenViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.absen_holder,parent,false))
    }

    override fun onBindViewHolder(holder: AbsenViewHolder, position: Int) {
        holder.bindData(this@AbsenAdapter,position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}