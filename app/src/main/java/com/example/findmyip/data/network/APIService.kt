package com.example.findmyip.data.network

import com.example.findmyip.data.model.ResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("json/")
    suspend fun getMyIPInfo():Response<ResponseDTO>

}