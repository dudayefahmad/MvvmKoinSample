package com.example.mvvmkoinsample.data.repository

import com.example.mvvmkoinsample.data.remote.ApiService
import com.example.mvvmkoinsample.data.remote.model.request.LaunchCodeReq
import com.example.mvvmkoinsample.data.remote.model.response.LaunchCode
import com.example.mvvmkoinsample.data.source.LaunchCodeSource

class LauchCodeRepository(private val apiService: ApiService) : LaunchCodeSource {

    override suspend fun validate(code: String): LaunchCode {
        return apiService.validateLaunchCode(LaunchCodeReq(code))
    }

}