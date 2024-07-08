package com.bypriyan.todoapp.viewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypriyan.todoapp.Model.ModelCurrentWeatherResponce
import com.bypriyan.todoapp.repositry.CurrentWeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DateViewModel @Inject constructor(val currentWeatherRepo: CurrentWeatherRepo) :ViewModel() {

    private val _currentWeather = MutableLiveData<ModelCurrentWeatherResponce>()
    val currentWeather: LiveData<ModelCurrentWeatherResponce> = _currentWeather

    fun getWeatherData(city:String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                currentWeatherRepo.getCurrentWeather(city).collect{modelCurrentWeatherResponce ->
                    _currentWeather.postValue(modelCurrentWeatherResponce)
                }
            }
        }
    }

}