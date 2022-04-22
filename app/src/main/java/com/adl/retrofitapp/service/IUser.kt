package com.adl.retrofitapp.service

import com.adl.retrofitapp.model.GetUserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IUser {

    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @GET("api/table_user/all?")
    fun getAll(@Query("limit") query:String): Call<GetUserResponse>

    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @GET("api/table_user/all?")
    fun login(@Query("filter")query: String): Call<GetUserResponse>

    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @GET("api/table_user/detail")
    fun getUser(@Field("id") id:String): Call<GetUserResponse>

}