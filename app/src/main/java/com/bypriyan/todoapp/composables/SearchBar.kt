package com.bypriyan.todoapp.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.bypriyan.todoapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchBar(modifier: Modifier){

    var query by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }


    SearchBar(query = query,
        onQueryChange = {
            query = it
        },
        onSearch = {},
        active = isActive,
        onActiveChange = {
            isActive = it
        },
        modifier = modifier.padding(if(isActive){ 0.dp }else{ 15.dp }),
        placeholder = {
            Text(text = "Search location..",
                color = colorResource(id = R.color.dark_greay))
        },
        leadingIcon = {
            androidx.compose.material.Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        },
        trailingIcon = {
            IconButton(onClick = {if(query.isNotEmpty())query= "" else isActive = false},
                enabled = isActive) {
                Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
            }
        }) {
        //content


    }

}