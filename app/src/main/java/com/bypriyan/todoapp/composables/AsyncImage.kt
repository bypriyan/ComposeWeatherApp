package com.bypriyan.todoapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bypriyan.todoapp.R

@Composable
fun loadImage(url: String, dp: Dp, modifier: Modifier){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = "Image", // Provide a description for accessibility
        modifier = modifier
            .size(dp) // Adjust size as needed
            .background(color = colorResource(id = R.color.transparent)), // Add rounded corners
        contentScale = ContentScale.FillBounds // Adjust scaling as needed
    )
}