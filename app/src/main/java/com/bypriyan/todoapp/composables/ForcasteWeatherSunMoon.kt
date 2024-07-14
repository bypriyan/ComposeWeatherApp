package com.bypriyan.todoapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bypriyan.todoapp.Model.ModelCurrentWeatherResponce
import com.bypriyan.todoapp.Model.forcaste.ModelWeatherForcase
import com.bypriyan.todoapp.R

@Composable
fun forcasteWeatherDataSunMoon(data: ModelWeatherForcase) {

    Column {

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .background(
                color = colorResource(id = R.color.bg),
                shape = RoundedCornerShape(20.dp)
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){

            element(
                R.drawable.sunrise,"Sunrise", data.forecast.forecastday.get(0).astro.sunrise, modifier = Modifier.weight(0.5f),
                tint = R.color.yellow)

            element(
                R.drawable.sunset,"Sunset", data.forecast.forecastday.get(0).astro.sunset, modifier = Modifier.weight(0.5f),
                tint = R.color.orange)

        }

        Spacer(modifier = Modifier.height(20.dp))

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .background(
                color = colorResource(id = R.color.bg),
                shape = RoundedCornerShape(20.dp)
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){

            element(
                R.drawable.moon_rais,"moonrise", data.forecast.forecastday.get(0).astro.moonrise, modifier = Modifier.weight(0.5f),
                tint = R.color.limeGreay)

            element(
                R.drawable.moon_set,"moonset", data.forecast.forecastday.get(0).astro.moonset, modifier = Modifier.weight(0.5f),
                tint = R.color.lite_greay)

        }

    }
}
