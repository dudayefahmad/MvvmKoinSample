package com.example.mvvmkoinsample.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

suspend fun delayInDefault(timeMillis: Long){
    withContext(Dispatchers.Default) {
        delay(timeMillis)
    }
}