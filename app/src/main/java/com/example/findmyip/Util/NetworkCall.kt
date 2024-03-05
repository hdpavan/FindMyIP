package com.example.findmyip.Util

import android.util.Log
import retrofit2.Response
import retrofit2.Retrofit

inline fun <T> execute(apiCall: () -> Response<T>): APIResponse<T> {

    return try {

        val response = apiCall.invoke()
        Log.d("APIExecute","URL ${response}")

        if (response.isSuccessful) {
            APIResponse.Success(data = response.body())
        } else {
            APIResponse.Error(error = response.message())
        }

    } catch (e: Exception) {
        APIResponse.Error(error = "Something went wrong")
    }

}