package com.bypriyan.todoapp.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
    @Preview(showBackground = true)
    @Composable
    fun homeScreen(){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            topAppContent();
        }
    }

@Preview(showBackground = true)
@Composable
fun topAppContent() {
    Row(Modifier.fillMaxWidth()){

    }
}
