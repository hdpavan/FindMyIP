package com.example.findmyip.data.repository

import com.example.findmyip.Util.APIResponse
import com.example.findmyip.Util.execute
import com.example.findmyip.data.mapper.toMyIpInfo
import com.example.findmyip.data.network.APIService
import com.example.findmyip.domain.model.MyIPInfo
import com.example.findmyip.domain.repository.IRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val apiService: APIService) : IRepository {

    override suspend fun getMyIpInfo(): APIResponse<MyIPInfo> {

        val data: APIResponse<MyIPInfo> =
            when (val response = execute { apiService.getMyIPInfo() }) {
                is APIResponse.Success -> APIResponse.Success(response.data?.toMyIpInfo())

                is APIResponse.Error -> APIResponse.Error(response.error)

                else -> APIResponse.Loading()
            }

        return data
    }
}