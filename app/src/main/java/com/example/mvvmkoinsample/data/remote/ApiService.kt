package com.example.mvvmkoinsample.data.remote

import com.example.mvvmkoinsample.data.remote.model.response.Goals
import com.example.mvvmkoinsample.data.remote.model.request.LaunchCodeReq
import com.example.mvvmkoinsample.data.remote.model.response.LaunchCode
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("v1/launchcode")
    suspend fun validateLaunchCode(@Body code: LaunchCodeReq): LaunchCode

    @GET("v1/goal")
    suspend fun getListGoal(): List<Goals>

}