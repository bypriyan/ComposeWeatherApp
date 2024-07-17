package com.bypriyan.todoapp.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bypriyan.todoapp.R
import com.bypriyan.todoapp.networkResp.NetworkResponce
import com.bypriyan.todoapp.viewModel.PlaceViewModel
import com.bypriyan.todoapp.viewModel.WeatherViewModel

    @Composable
    fun homeScreen(
        weatherViewModel: WeatherViewModel,
        placeViewModel: PlaceViewModel,
        currentTime: Int
    ) {

        val currentWeatherStatus = weatherViewModel.currentWeatherStatus.observeAsState()
        val selectedPlace by placeViewModel.selectedPlace.collectAsState()

        var placeName by remember { mutableStateOf("*") }

        selectedPlace?.let{ place ->
            if(!placeName.equals(place)){
                Log.d("sss", "homeScreen: city = $place")
                placeName = selectedPlace!!
                weatherViewModel.getWeatherData(placeName)
                weatherViewModel.getForcasteWeatherData(placeName)
            }
        }

        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center)
        {

            if(selectedPlace!=null){
                Image(
                    painter = painterResource(
                        id = R.drawable.night_bg
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(
                            radiusX = 40.dp,
                            radiusY = 40.dp
                        )
                )
            }else{
                lottieAnimation(R.raw.weather_city)
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                ) {
                searchBar(weatherViewModel,placeViewModel, modifier = Modifier.fillMaxWidth())

                when(val result = currentWeatherStatus.value){
                    is NetworkResponce.Loading -> {
                        Column (modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center){
                            progressBar()
                        }

                    }

                    is NetworkResponce.Error -> {
                        Log.d("tagla", "homeScreen: error")
                        

                    }
                    is NetworkResponce.Success -> {
                        weatherUiData(result.data, Modifier.fillMaxWidth(), weatherViewModel)
                    }
                    else -> {
                    }

                }              
            }

        }
    }

