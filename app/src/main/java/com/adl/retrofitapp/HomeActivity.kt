package com.adl.retrofitapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.adl.retrofitapp.model.TableUserItem
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == Activity.RESULT_OK){

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var currentUser =intent?.getParcelableExtra<TableUserItem>("data")
        btn_checkin.setOnClickListener({

            val intent = Intent(this@HomeActivity, CheckinActivity::class.java)
            intent.putExtra("data", currentUser)
            resultLauncher.launch(intent)
        })
        btn_history.setOnClickListener({
            val intent = Intent(this@HomeActivity, HistoryActivity::class.java)
            intent.putExtra("data", currentUser)
            startActivity(intent)
        })
    }


}