package com.example.mvvmkoinsample.di

import android.util.Log
import com.example.mvvmkoinsample.BuildConfig
import com.example.mvvmkoinsample.data.Header
import com.example.mvvmkoinsample.data.remote.ApiService
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single(named("logging")) {
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.d("ApiLog", message)
        }).setLevel(HttpLoggingInterceptor.Level.BODY) as Interceptor
    }

    single(named("header")) {
        Interceptor { chain ->
            val request = chain.request()
            val requestBuilder = request.newBuilder()
                .addHeader(Header.API_KEY, BuildConfig.API_URL)
                .addHeader(Header.VERSION, BuildConfig.VERSION_NAME)

            chain.proceed(requestBuilder.build())
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(named("logging")))
            .addInterceptor(get<Interceptor>(named("header")))
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create(get() as Gson))
            .client(get() as OkHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}