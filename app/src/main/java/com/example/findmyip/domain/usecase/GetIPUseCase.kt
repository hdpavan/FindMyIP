package com.example.findmyip.domain.usecase

import android.util.Log
import com.example.findmyip.Util.APIResponse
import com.example.findmyip.data.repository.RepositoryImpl
import com.example.findmyip.domain.model.MyIPInfo
import com.example.findmyip.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetIPUseCase @Inject constructor(private val repository: IRepository) {

    operator fun invoke(): Flow<APIResponse<MyIPInfo>> {
        return flow {
            emit(APIResponse.Loading())
            emit(repository.getMyIpInfo())
        }
    }
}