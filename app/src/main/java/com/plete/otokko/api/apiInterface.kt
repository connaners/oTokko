package com.plete.otokko.api

import com.google.gson.JsonObject
import com.plete.otokko.model.DefaultResponse
import com.plete.otokko.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface apiInterface {
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation:String
    ):Call<DefaultResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<LoginResponse>

    @GET("categories")
    fun getCategories(@Header("Authorization") authHeader: String):Call<JsonObject>

    @GET("products?searchByCategory/{categoryId}")
    fun getProductByCategory(@Header("Authorization") authHeader: String, @Path("categoryId") id:Int):Call<JsonObject>
}