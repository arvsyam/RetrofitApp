package com.adl.retrofitapp.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.absen_holder.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AbsenViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val tanggal = view.txtTanggal
    val masuk = view.txtMasuk
    val pulang = view.txtPulang

    fun bindData(adapter:AbsenAdapter, position:Int){
        val cal = Calendar.getInstance()
        var cekOut = adapter.data.get(position)?.logout

        val cekout = SimpleDateFormat("HH:mm").format(cal.time)

        tanggal.setText(adapter.data.get(position)?.login)
        masuk.setText(adapter.data.get(position)?.login)
//        masuk.setText(formattedDate)

        }

}