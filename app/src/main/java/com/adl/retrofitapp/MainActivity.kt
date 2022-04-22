package com.adl.retrofitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.adl.retrofitapp.model.GetUserResponse
import com.adl.retrofitapp.model.TableUserItem
import com.adl.retrofitapp.service.RetrofitConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener({
            login()
        })
    }

    fun login(){
        RetrofitConfig().getService().login(et_username.text.toString()).enqueue(object: Callback<GetUserResponse>{
            override fun onResponse(
                call: Call<GetUserResponse>,
                response: Response<GetUserResponse>
            ) {
                var data = response.body()?.data?.tableUser

                if(response.isSuccessful){
                    Log.d("data","${data}")
                    if (data?.size == 0){
                        Toast.makeText(this@MainActivity,"Username or Password is doesn't found",Toast.LENGTH_LONG).show()
                    }else{

                        var currentUser = response.body()?.data?.tableUser?.get(0)
                        if(currentUser?.password == et_password.text.toString()){
                            Toast.makeText(this@MainActivity,"Login Successfull",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@MainActivity, HomeActivity::class.java)
                            intent.putExtra("data", currentUser)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this@MainActivity,"Incorrect password",Toast.LENGTH_LONG).show()
                        }

                    }
                }else{
                    Toast.makeText(this@MainActivity,"username and password can't empty",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {

            }

        })
    }
}