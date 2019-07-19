package com.example.mvvmkoinsample.data.repository

import com.example.mvvmkoinsample.data.remote.ApiService
import com.example.mvvmkoinsample.data.remote.model.response.Goals
import com.example.mvvmkoinsample.data.source.GoalDataSource

class GoalRepository(
    private val apiService: ApiService
) : GoalDataSource {

    override suspend fun getGoalList(): List<Goals> {
        return apiService.getListGoal()
    }

}