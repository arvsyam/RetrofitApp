package com.adl.retrofitapp

import android.app.Activity
import android.content.Context
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.adl.retrofitapp.model.PostAbsenResponse
import com.adl.retrofitapp.model.TableUserItem
import com.adl.retrofitapp.service.RetrofitConfigAbsen

import com.github.drjacky.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_checkin.*

import com.robin.locationgetter.EasyLocation

import kotlinx.android.synthetic.main.activity_main.*
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


class CheckinActivity : AppCompatActivity() {
    lateinit var photoURI: Uri
    var isUpload = false
    lateinit var latitude:String
    lateinit var longitude:String

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data!!
            photoURI = uri
            absen_image.setImageURI(uri)
            Log.d("uri image","${uri}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin)


        setLocation()

        var currentUser =intent?.getParcelableExtra<TableUserItem>("data")

        imageButton.setOnClickListener({
           pickImage()
            imageButton.setVisibility(View.GONE);
            isUpload = true
        })

        btn_absen_login.setOnClickListener({
            if (isUpload){
                saveData(currentUser!!)

            }else{
                Toast.makeText(this, "failed",Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun pickImage(){
        cameraLauncher.launch(
            ImagePicker.with(this)
                .crop()
                .cameraOnly()
                .maxResultSize(480, 800, true)
                .createIntent()
        )
    }

    fun saveData(user:TableUserItem){
        val localDate = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
        val time = sdf.format(localDate.time)




        RetrofitConfigAbsen().getService()
            .addAbsen(createRB(user.username!!),createRB(time.toString()),createRB(""), createRB("${latitude} , ${longitude}"),uploadImage(photoURI,"image"))
            .enqueue(object : Callback<PostAbsenResponse> {
                override fun onResponse(
                    call: Call<PostAbsenResponse>,
                    response: Response<PostAbsenResponse>
                ) {
                    if(response.isSuccessful()){
                        Log.d("resp","${response.body()}")

                        Toast.makeText(this@CheckinActivity,"Checkin Successfull", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Log.d("not resp","${response.body()}")
                        Toast.makeText(this@CheckinActivity,"not Saved", Toast.LENGTH_LONG).show()
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

    fun uploadImage(uri:Uri,param:String): MultipartBody.Part {
        val file: File = File(uri.path)
        val rb: RequestBody =  file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(param,file.name,rb)
    }

    fun setLocation(){
        val loc = EasyLocation(this@CheckinActivity, object: EasyLocation.EasyLocationCallBack{
            override fun permissionDenied() {

                Log.i("Location", "permission  denied")


            }

            override fun locationSettingFailed() {

                Log.i("Location", "setting failed")


            }

            override fun getLocation(location: Location) {
                latitude = location?.latitude.toString()
                longitude = location?.longitude.toString()
                Log.i("Location_lat_lng"," latitude ${location?.latitude} longitude ${location?.longitude}")



            }
        })
    }
}