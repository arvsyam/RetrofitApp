package com.adl.retrofitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.adl.retrofitapp.model.AbsenItem
import com.adl.retrofitapp.model.TableUserItem
import kotlinx.android.synthetic.main.activity_status.*

class Status : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        var currentAbsen =intent?.getParcelableExtra<AbsenItem>("data")

        if (currentAbsen != null){
            txtStatus.setText("Login Foto Selfie Berhasil !!")
            txtGeo.setText("Get Tag : ${currentAbsen.location}")
            btn_done.setOnClickListener({
                finish()
            })
        }else{
            txtStatus.setText("Login Foto Selfie Gagal !!")
            imgStatus.setImageResource(R.drawable.cross)
            btn_done.setText("Re-Scan")
            txtGeo.isVisible = false
            btn_done.setOnClickListener({
                finish()


            })
        }
    }

    override fun onRestart() {
        super.onRestart()

        var currentAbsen =intent?.getParcelableExtra<AbsenItem>("data")

        if (currentAbsen != null){
            txtStatus.setText("Login Foto Selfie Berhasil !!")
            txtGeo.setText("Get Tag : ${currentAbsen.location}")
            btn_done.setOnClickListener({
                finish()
            })
        }else{
            txtStatus.setText("Login Foto Selfie Gagal !!")
            imgStatus.setImageResource(R.drawable.cross)
            btn_done.setText("Re-Scan")
            txtGeo.isVisible = false
            btn_done.setOnClickListener({
                finish()


            })
        }
    }
}