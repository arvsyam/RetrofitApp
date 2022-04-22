package com.adl.retrofitapp

import android.app.Activity
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.github.drjacky.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_checkin.*

class CheckinActivity : AppCompatActivity() {
    lateinit var photoURI: Uri
    var isUpload = false

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
        imageButton.setOnClickListener({
           pickImage()
            imageButton.setVisibility(View.GONE);
            isUpload = true
        })

        btn_absen_login.setOnClickListener({
            if (isUpload){
                Toast.makeText(this, "success",Toast.LENGTH_SHORT).show()
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
}