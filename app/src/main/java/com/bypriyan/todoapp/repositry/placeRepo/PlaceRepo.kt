package com.bypriyan.togocartuser.repo.placeRepo

import android.util.Log
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PlaceRepo @Inject constructor(
    val placesClient: PlacesClient,
    val autocompleteSessionToken: AutocompleteSessionToken
) {

    fun findAutoCompletePlacePredictions(query: String): Flow<List<AutocompletePrediction>> = flow {
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .setCountries(listOf("IN"))
            .setSessionToken(autocompleteSessionToken)
            .build()

        val response = placesClient.findAutocompletePredictions(request).await()
        emit(response.autocompletePredictions)
        Log.d("TAG", "getPlacePredictions: repo 1st ${Thread.currentThread().name}")
    }.catch { exception ->
        Log.e("TAG", "Some exception happened: ${exception.message}")
        emit(emptyList())
    }

    suspend fun findPlaceLatLong(placeId:String):Flow<Place> = flow {
        val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
        val request = FetchPlaceRequest.builder(placeId, placeFields).build()
        val response = placesClient.fetchPlace(request).await()
        Log.d("TAG", "findPlaceLatLong: place lat long is ${response}")
        emit(response.place)
    }.catch { exception ->
        Log.e("TAG", "Some exception happened: ${exception.message}")
    }


}
