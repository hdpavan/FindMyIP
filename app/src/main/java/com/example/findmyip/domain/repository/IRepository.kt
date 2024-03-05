package com.example.findmyip.domain.repository

import com.example.findmyip.Util.APIResponse
import com.example.findmyip.domain.model.MyIPInfo

interface IRepository {
    suspend fun getMyIpInfo():APIResponse<MyIPInfo>
}