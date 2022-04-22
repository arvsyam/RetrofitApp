package com.adl.retrofitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adl.retrofitapp.model.TableUserItem
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var currentUser =intent?.getParcelableExtra<TableUserItem>("data")
        btn_checkin.setOnClickListener({
            val intent = Intent(this@HomeActivity, CheckinActivity::class.java)
            intent.putExtra("data", currentUser)
            startActivity(intent)
        })
    }
}