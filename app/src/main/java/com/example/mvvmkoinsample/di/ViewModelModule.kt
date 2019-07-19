package com.example.mvvmkoinsample.di

import com.example.mvvmkoinsample.presentation.goals.GoalViewModel
import com.example.mvvmkoinsample.presentation.launchcode.LaunchCodeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { LaunchCodeViewModel(get()) }
    viewModel { GoalViewModel(get()) }
}

