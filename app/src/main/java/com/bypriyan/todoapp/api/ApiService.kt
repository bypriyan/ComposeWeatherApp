package com.bypriyan.togocartstore.api

import com.bypriyan.todoapp.Model.ModelCurrentWeatherResponce
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

    @GET("GetLiveMarkets2_DpKalyan.php")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") city:String
    ): Response<ModelCurrentWeatherResponce>

}