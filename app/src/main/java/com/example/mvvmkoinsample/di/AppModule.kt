package com.example.mvvmkoinsample.di

import com.google.gson.GsonBuilder
import org.koin.dsl.module

val appModule = module {
    single { GsonBuilder().setLenient().create() }
}