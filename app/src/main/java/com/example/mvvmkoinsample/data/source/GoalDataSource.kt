package com.example.mvvmkoinsample.data.source

import com.example.mvvmkoinsample.data.remote.model.response.Goals

interface GoalDataSource {
    suspend fun getGoalList(): List<Goals>
}