package com.example.mvvmkoinsample.data.source

import com.example.mvvmkoinsample.data.remote.model.response.LaunchCode

interface LaunchCodeSource {

    suspend fun validate(code:String): LaunchCode

}