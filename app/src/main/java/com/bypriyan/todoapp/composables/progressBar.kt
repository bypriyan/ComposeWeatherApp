package com.bypriyan.todoapp.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bypriyan.todoapp.R


@Composable
fun progressBar(){
    CircularProgressIndicator(
        modifier = Modifier.size(34.dp), // Use size instead of width
        color = colorResource(id = R.color.teal_200),
        trackColor = colorResource(id = R.color.transparent),
    )
}