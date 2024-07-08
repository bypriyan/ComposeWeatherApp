package com.bypriyan.todoapp.networkResp

sealed class NetworkResponce<out T> {
    data class Success<T>(val data: T) : NetworkResponce<T>()
    data class Error(val message: String) : NetworkResponce<Nothing>()
    object Loading : NetworkResponce<Nothing>()

}