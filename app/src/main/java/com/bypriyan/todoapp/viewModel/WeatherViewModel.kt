package com.bypriyan.todoapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypriyan.todoapp.Model.ModelCurrentWeatherResponce
import com.bypriyan.todoapp.Model.forcaste.ModelWeatherForcase
import com.bypriyan.todoapp.networkResp.NetworkResponce
import com.bypriyan.todoapp.repositry.CurrentWeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(val currentWeatherRepo: CurrentWeatherRepo) :ViewModel() {

    private val _currentWeatherstatus = MutableLiveData<NetworkResponce<ModelCurrentWeatherResponce>>()
    val currentWeatherStatus: LiveData<NetworkResponce<ModelCurrentWeatherResponce>> = _currentWeatherstatus

    private val _forcasteWeatherstatus = MutableLiveData<NetworkResponce<ModelWeatherForcase>>()
    val forcasteWeatherstatus: LiveData<NetworkResponce<ModelWeatherForcase>> = _forcasteWeatherstatus


    fun getWeatherData(city:String) {
        _currentWeatherstatus.postValue(NetworkResponce.Loading)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                currentWeatherRepo.getCurrentWeather(city).collect{modelCurrentWeatherResponce ->
                    _currentWeatherstatus.postValue(modelCurrentWeatherResponce)
                }
            }
        }
    }

    fun getForcasteWeatherData(city:String) {
        _forcasteWeatherstatus.postValue(NetworkResponce.Loading)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                currentWeatherRepo.getWeatherForcaste(city).collect{modelforcasteWeatherResponce ->
                    _forcasteWeatherstatus.postValue(modelforcasteWeatherResponce)
                }
            }
        }
    }

}