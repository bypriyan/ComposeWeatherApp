package com.bypriyan.todoapp.viewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypriyan.todoapp.repositry.DateRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DateViewModel @Inject constructor(val dateRepo: DateRepo) :ViewModel() {

    private val _months = MutableLiveData<List<Pair<String,String>>>()
    val months: LiveData<List<Pair<String,String>>> = _months

    fun getMarketData() {
        viewModelScope.launch {

        }
    }

}