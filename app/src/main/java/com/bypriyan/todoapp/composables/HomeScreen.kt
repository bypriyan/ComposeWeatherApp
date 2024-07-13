package com.bypriyan.todoapp.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
        Log.d("selected", "homeScreen: $selectedPlace")

        val backgroundImageResId = when {
            currentTime > 19 || currentTime in 0..5 -> R.drawable.night_bg
            currentTime in 5..16 -> R.drawable.morning_bg
            else -> R.drawable.evening_bg
        }

        Box(modifier = Modifier
            .fillMaxSize()){

            Image(
                painter = painterResource(
                    id = backgroundImageResId
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

            when(currentWeatherStatus.value){
                is NetworkResponce.Loading -> {
                    progressBar()
                }

                is NetworkResponce.Error -> {
                    Log.d("tagla", "homeScreen: error")

                }

                is NetworkResponce.Success -> {

                }
                else -> {
                    progressBar()
                }

            }
        }

        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            searchBar(placeViewModel, modifier = Modifier.fillMaxWidth())



        }
    }
