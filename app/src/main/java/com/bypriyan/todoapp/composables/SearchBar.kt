package com.bypriyan.todoapp.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bypriyan.todoapp.R
import com.bypriyan.todoapp.viewModel.PlaceViewModel
import com.google.android.libraries.places.api.model.AutocompletePrediction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchBar(placeViewModel: PlaceViewModel, modifier: Modifier) {

    var query by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }

    val predictions by placeViewModel.predictions.collectAsState()

    SearchBar(query = query,
        onQueryChange = {
            query = it
            placeViewModel.getPlacePredictions(it)
        },
        onSearch = {},
        active = isActive,
        onActiveChange = {
            isActive = it
        },
        modifier = modifier.padding(if (isActive) { 0.dp } else { 15.dp }),
        placeholder = {
            Text(text = "Search location..", color = colorResource(id = R.color.dark_greay))
        },
        leadingIcon = {
            androidx.compose.material.Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        },
        trailingIcon = {
            IconButton(onClick = { if (query.isNotEmpty()) query = "" else isActive = false }, enabled = isActive) {
                Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
            }
        }) {
        // Show PredictionsList below the search field if there are predictions
        if (predictions.isNotEmpty()) {
            PredictionsList(predictions, placeViewModel) {
                Log.d("clicked", "searchBar: button clicked ")
                isActive = false
                query = it
            }
        }

    }
}

@Composable
fun PredictionsList(predictions: List<AutocompletePrediction>, viewModel: PlaceViewModel, onClick: (String) -> Unit) {
    LazyColumn {
        items(predictions) { prediction ->
            searchedPlaces(prediction, viewModel) {
                onClick(it)
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun searchedPlaces(prediction: AutocompletePrediction, viewModel: PlaceViewModel, onClick: (String) -> Unit) {
    Spacer(modifier = Modifier.height(20.dp))
    ConstraintLayout(
        modifier = Modifier
            .clickable {
                viewModel.fetchPlaceDetails(prediction.placeId)
                onClick(prediction.getPrimaryText(null).toString())
            }
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .background(
                shape = RoundedCornerShape(10.dp),
                color = colorResource(id = R.color.white)
            )
            .padding(10.dp)

    ) {
        val (locationImage, column) = createRefs()

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    colorResource(id = R.color.limeGreay),
                    shape = RoundedCornerShape(10.dp)
                )
                .constrainAs(locationImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                }, contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.location_icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(id = R.color.appColor)),
                modifier = Modifier.size(22.dp)
            )
        }

        Column(modifier = Modifier
            .padding(start = 8.dp, end = 40.dp)
            .constrainAs(column) {
                top.linkTo(parent.top)
                start.linkTo(locationImage.end, margin = 10.dp)
                centerVerticallyTo(parent)
            }) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = prediction.getPrimaryText(null).toString(),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.medium)),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = prediction.getFullText(null).toString(),
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.light)),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
