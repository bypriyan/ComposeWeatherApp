package com.bypriyan.todoapp.composables

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bypriyan.todoapp.Model.forcaste.Hour
import com.bypriyan.todoapp.Model.forcaste.ModelWeatherForcase
import com.bypriyan.todoapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun hourlyWeatherForcast(data: ModelWeatherForcase) {

    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 15.dp)
        .background(
            color = colorResource(id = R.color.bg),
            shape = RoundedCornerShape(20.dp)
        )
        .padding(10.dp)) {

        items(data.forecast.forecastday.get(0).hour){ hour->
            hourlyWeatherData(hour)
        }
    }

}

@Composable
fun hourlyWeatherData(hour: Hour) {
    Column(
        Modifier
            .wrapContentWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = timestampToTime(hour.time_epoch.toString()),
            color = colorResource(id = R.color.white),
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.semibold)))

        Spacer(modifier = Modifier.height(10.dp))

        loadImage(url = "https:${hour.condition.icon}", dp = 40.dp , modifier =Modifier )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "${hour.temp_c}°",
            color = colorResource(id = R.color.white),
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.semibold)))
    }
}

fun timestampToTime(s: String): String {
    val timestampLong = s.toLong()
    val date = Date(timestampLong * 1000) // convert seconds to milliseconds
    val format = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
    val formattedDate = format.format(date)
    return formattedDate.replace("AM", "am").replace("PM", "pm")
}