package com.bypriyan.todoapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bypriyan.todoapp.Model.ModelCurrentWeatherResponce
import com.bypriyan.todoapp.R


@Composable
fun windsCurrentWeather(data: ModelCurrentWeatherResponce) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 15.dp)
        .background(
            color = colorResource(id = R.color.bg),
            shape = RoundedCornerShape(20.dp)
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center){

        element(R.drawable.humidity,"Humidity", data.current.humidity, modifier = Modifier.weight(0.5f),
            tint = R.color.white)

        element(R.drawable.uv,"UV index", data.current.uv, modifier = Modifier.weight(0.5f),
            tint = R.color.yellow)

    }
}

@Composable
fun element(icon: Int, topic: String, data:String, modifier: Modifier,
            tint: Int) {

    ConstraintLayout(modifier = modifier.fillMaxWidth()) {

        val (iconc, titlec, datac) = createRefs()

        Icon(painter = painterResource(id = icon), contentDescription = null,
            modifier = Modifier
                .padding(20.dp)
                .size(30.dp)
                .constrainAs(iconc) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                },
            tint = colorResource(tint))

        Text(text = topic,
            color = colorResource(id = R.color.white),
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.bold)),
            modifier = Modifier.constrainAs(titlec){
                start.linkTo(iconc.end)
                top.linkTo(parent.top, margin = 18.dp)
            })

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = data,
            color = colorResource(id = R.color.white),
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.bold)),
            modifier = Modifier.constrainAs(datac){
                start.linkTo(iconc.end)
                top.linkTo(titlec.top, margin = 15.dp)
            }
        )
    }
}