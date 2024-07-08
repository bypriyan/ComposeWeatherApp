package com.bypriyan.todoapp.repositry

import com.bypriyan.todoapp.Model.ModelCurrentWeatherResponce
import com.bypriyan.todoapp.utility.Constants
import com.bypriyan.togocartstore.api.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrentWeatherRepo @Inject constructor(val apiService: ApiService){

    suspend fun getCurrentWeather(city:String):Flow<ModelCurrentWeatherResponce> = flow {
        val responce = apiService.getCurrentWeather(Constants.apiKey,city)

        if(responce.isSuccessful){
            emit(responce.body()!!)
        }else{
            emit(ModelCurrentWeatherResponce(null, null))
        }
    }.catch {
        emit(ModelCurrentWeatherResponce(null, null))
    }

}