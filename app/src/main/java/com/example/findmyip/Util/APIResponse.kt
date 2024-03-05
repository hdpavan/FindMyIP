package com.example.findmyip.Util

sealed class APIResponse<T>(val error: String? = null, val data: T? = null) {

    class Loading<T>() : APIResponse<T>()
    class Success<T>( data: T?) : APIResponse<T>(data = data)
    class Error<T>(error: String?) : APIResponse<T>(error = error)
}