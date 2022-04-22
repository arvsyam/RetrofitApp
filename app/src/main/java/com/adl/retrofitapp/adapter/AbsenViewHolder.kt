package com.adl.retrofitapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.absen_holder.view.*
import java.text.SimpleDateFormat
import java.util.*

class AbsenViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val tanggal = view.txtTanggal
    val masuk = view.txtMasuk
    val pulang = view.txtPulang

    fun bindData(adapter:AbsenAdapter, position:Int){




        val dateLogin = adapter.data.get(position)?.login?.split(" ")
        val dateLogout =adapter.data.get(position)?.logout?.split(" ")



        tanggal.setText("${dateLogin?.get(0)}")
        masuk.setText("${dateLogin?.get(1)}")
        pulang.setText("${dateLogout?.get(1)}")


        }

}