package com.adl.retrofitapp.service

import com.adl.retrofitapp.model.GetAbsenResponse
import com.adl.retrofitapp.model.GetUserResponse
import com.adl.retrofitapp.model.PostAbsenResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface IAbsen {

    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @GET("api/absen/all?")
    fun getAll(@Query("limit") query:String): Call<GetAbsenResponse>

    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @GET("api/absen/all?")
    fun getUserAbsen(@Query("filter") filter:String,
                     @Query("limit") limit:String): Call<GetAbsenResponse>

    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @GET("api/absen/all?")
    fun getLast(@Query("limit") query:String,
                @Query("sort_order") order:String): Call<GetAbsenResponse>

    @Multipart
    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @POST("api/absen/add")
    fun addAbsen(@Part("username") username: RequestBody,
                 @Part ("login") login: RequestBody,
                 @Part("logout") logout: RequestBody,
                 @Part("location") body: RequestBody,
                 @Part file: MultipartBody.Part):Call<PostAbsenResponse>

    @Multipart
    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @POST("api/absen/update")
    fun updateAbsen(@Part ("id") id:RequestBody,
                @Part("username") username: RequestBody,
                 @Part ("login") login: RequestBody,
                 @Part("logout") logout: RequestBody,
                 @Part("location") body: RequestBody):Call<PostAbsenResponse>



}