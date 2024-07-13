package com.bypriyan.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.bypriyan.todoapp.composables.homeScreen
import com.bypriyan.todoapp.viewModel.PlaceViewModel
import com.bypriyan.todoapp.viewModel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val weatherViewModel:WeatherViewModel by viewModels()
    private val placeViewModel:PlaceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dateFormat = SimpleDateFormat("HH", Locale.getDefault())
        val currentTime = dateFormat.format(Date()).toInt()

        setContent {
            homeScreen(weatherViewModel,placeViewModel, currentTime)
        }

    }
}