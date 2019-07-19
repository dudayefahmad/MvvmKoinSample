package com.example.mvvmkoinsample.di

import com.example.mvvmkoinsample.data.repository.GoalRepository
import com.example.mvvmkoinsample.data.repository.LauchCodeRepository
import com.example.mvvmkoinsample.data.source.GoalDataSource
import com.example.mvvmkoinsample.data.source.LaunchCodeSource
import org.koin.dsl.module

val repositoryModule = module {

    single { LauchCodeRepository(get()) as LaunchCodeSource }
    single { GoalRepository(get()) as GoalDataSource }

}