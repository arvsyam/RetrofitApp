package com.adl.retrofitapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.adl.retrofitapp.model.AbsenItem
import com.adl.retrofitapp.model.GetAbsenResponse
import com.adl.retrofitapp.model.PostAbsenResponse
import com.adl.retrofitapp.model.TableUserItem
import com.adl.retrofitapp.service.RetrofitConfigAbsen
import kotlinx.android.synthetic.main.activity_home.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {
    var isCheckin = false
    lateinit var photoURI: Uri
    lateinit var currentAbsen:AbsenItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var currentUser =intent?.getParcelableExtra<TableUserItem>("data")

        btn_checkout.setVisibility(View.GONE);
        btn_history.isEnabled = false
        txtCek.setText("Check In")



        btn_checkin.setOnClickListener({
            val intent = Intent(this@HomeActivity, CheckinActivity::class.java)
            intent.putExtra("data", currentUser)
            startActivity(intent)
        })
        btn_history.setOnClickListener({
            val intent = Intent(this@HomeActivity, HistoryActivity::class.java)
            intent.putExtra("data", currentUser)
            startActivity(intent)
        })

        btn_checkout.setOnClickListener({
            getAbsen()
            finish()
        })
        btn_logout.setOnClickListener({
            finish()
        })

    }

    fun getAbsen(){
        RetrofitConfigAbsen().getService()
            .getLast("-&DESC")
            .enqueue(object : Callback<GetAbsenResponse> {
                override fun onResponse(
                    call: Call<GetAbsenResponse>,
                    response: Response<GetAbsenResponse>
                ) {
                    if(response.isSuccessful()){
                        Log.d("absen","${response.body()?.data?.absen?.get(0)}")

                        currentAbsen = response.body()?.data?.absen?.get(0)!!
                        updateAbsen(currentAbsen)

                    }else{
                        Log.d("not resp","${response.body()}")
                        Toast.makeText(this@HomeActivity,"not Saved", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<GetAbsenResponse>, t: Throwable) {
                    Log.e("error request",t.localizedMessage,t)
                }

            })
    }

    fun updateAbsen(absen:AbsenItem){
        val localDate = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
        val time = sdf.format(localDate.time)

        RetrofitConfigAbsen().getService()
            .updateAbsen(createRB(absen.id!!),createRB(absen.username!!),createRB(currentAbsen.login!!),createRB(time.toString()),createRB("10.023 , -12.23213"))
            .enqueue(object : Callback<PostAbsenResponse> {
                override fun onResponse(
                    call: Call<PostAbsenResponse>,
                    response: Response<PostAbsenResponse>
                ) {
                    if(response.isSuccessful()){
                        Log.d("resp","${response.body()}")
//                        Toast.makeText(this@CheckinActivity,"Saved", Toast.LENGTH_LONG).show()
//                        finish()
                    }else{
                        Log.d("not resp","${response.body()}")
                        Toast.makeText(this@HomeActivity,"not Saved", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<PostAbsenResponse>, t: Throwable) {
                    Log.e("error request",t.localizedMessage,t)
                }

            })
    }

    fun createRB(data:String): RequestBody {
        return RequestBody.create(MultipartBody.FORM,data)
    }

    fun uploadImage(uri: Uri, param:String): MultipartBody.Part {
        val file: File = File(uri.path)
        val rb: RequestBody =  file.asRequestBody("image/jpeg".toMediaTypeOrNull())

        return MultipartBody.Part.createFormData(param,file.name,rb)

    }

    override fun onRestart() {
        super.onRestart()
        btn_checkin.isEnabled = false
        btn_checkin.setVisibility(View.GONE);
        btn_checkout.setVisibility(View.VISIBLE);
        btn_checkin.isEnabled = true
        btn_history.isEnabled = true
        txtCek.setText("Check Out")
    }

}