package com.adl.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.adl.retrofitapp.adapter.AbsenAdapter
import com.adl.retrofitapp.model.AbsenItem
import com.adl.retrofitapp.model.GetAbsenResponse
import com.adl.retrofitapp.service.RetrofitConfigAbsen
import kotlinx.android.synthetic.main.activity_history.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryActivity : AppCompatActivity() {

    private lateinit var myAbsenAdapter: AbsenAdapter
    var listAbsen = ArrayList<AbsenItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        loadData()

    }

    fun loadData(){

            RetrofitConfigAbsen().getService()
                .getAll("-")
                .enqueue(object : Callback<GetAbsenResponse> {
                    override fun onFailure(call: Call<GetAbsenResponse>, t: Throwable) {
                        Toast.makeText(this@HistoryActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                    override fun onResponse(
                        call: Call<GetAbsenResponse>,
                        response: Response<GetAbsenResponse>
                    ) {
                        listAbsen = response.body()?.data?.absen as ArrayList<AbsenItem>
                        Log.d("Response","${listAbsen.size}")
                        myAbsenAdapter = ArrayList(listAbsen)?.let { AbsenAdapter(it) }
                        rcAbsen.apply{
                            layoutManager = LinearLayoutManager(this@HistoryActivity)
                            adapter = myAbsenAdapter
                        }
                    }
                })
    }
}