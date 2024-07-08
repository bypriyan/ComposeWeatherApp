package com.bypriyan.todoapp.repositry

import android.util.Log
import com.bypriyan.todoapp.Model.ModelCurrentWeatherResponce
import com.bypriyan.todoapp.networkResp.NetworkResponce
import com.bypriyan.todoapp.utility.Constants
import com.bypriyan.togocartstore.api.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrentWeatherRepo @Inject constructor(val apiService: ApiService){

    suspend fun getCurrentWeather(city:String):Flow<NetworkResponce<ModelCurrentWeatherResponce>> = flow {
        val responce = apiService.getCurrentWeather(Constants.apiKey,city)

        if(responce.isSuccessful){
            emit(NetworkResponce.Success(responce.body()!!))
        }else{
            emit(NetworkResponce.Error(responce.errorBody().toString()))
        }
    }.catch { e->
        emit(NetworkResponce.Error(e.message.toString()))
    }

}