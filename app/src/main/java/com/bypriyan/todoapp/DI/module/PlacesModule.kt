package com.bypriyan.todoapp.DI.module

import android.content.Context
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlacesModule {

    @Provides
    @Singleton
    fun providePlacesClient(@ApplicationContext context: Context): PlacesClient {
        if (!Places.isInitialized()) {
            Places.initialize(context, "xxxxxxxxxxxxxxxx")
        }
        return Places.createClient(context)
    }

    @Provides
    @Singleton
    fun provideAutocompleteSessionToken(): AutocompleteSessionToken {
        return AutocompleteSessionToken.newInstance()
    }
}