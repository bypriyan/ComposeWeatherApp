package com.bypriyan.todoapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bypriyan.todoapp.Model.ModelCurrentWeatherResponce
import com.bypriyan.todoapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun weatherUiData(data: ModelCurrentWeatherResponce, headerModifier: Modifier) {

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Spacer(modifier = Modifier.height(20.dp))
        Header(data, headerModifier)

        Spacer(modifier = Modifier.height(20.dp))
        windsCurrentWeather(data)
    }
}

@Composable
fun Header(data: ModelCurrentWeatherResponce, modifier: Modifier) {

    Column (modifier = modifier){


        Text(text = timestampToDate(data.current.last_updated_epoch),
            modifier
                .wrapContentWidth(),
            color = colorResource(id = R.color.white),
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = 13.sp,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(30.dp))

        digree(data)
        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Feels like ${data.current.feelslike_c}°",
            modifier
                .wrapContentWidth(),
            color = colorResource(id = R.color.lite_greay),
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = 13.sp,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = data.current.condition.text,
            modifier
                .wrapContentWidth(),
            color = colorResource(id = R.color.white),
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = 15.sp,
            textAlign = TextAlign.Center)

    }
}

@Composable
fun digree(data: ModelCurrentWeatherResponce){
    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center){

        loadImage(url = "https:${data.current.condition.icon}", 70.dp,
        Modifier)

        Spacer(modifier = Modifier.widthIn(10.dp))

        Text(text = "${data.current.temp_c}°",
            color = colorResource(id = R.color.white),
            fontFamily = FontFamily(Font(R.font.bold)),
            fontSize = 60.sp)

    }
}

fun timestampToDate(timestamp: String): String {
    val timestampLong = timestamp.toLong()
    val date = Date(timestampLong * 1000) // convert seconds to milliseconds
    val format = SimpleDateFormat("EEE dd MMM hh:mm a", Locale.ENGLISH)
    return format.format(date)
}