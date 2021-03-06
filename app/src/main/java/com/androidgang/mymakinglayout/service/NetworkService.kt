package com.androidgang.mymakinglayout.service

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    private const val BASE_URL = "https://ecommdb-d0e6.restdb.io/rest/"

    private val logger = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logger)

    private val builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient.apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("x-apikey", "ad8335d4f42951d735a37fb5032dd8c903e79")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build())

    private val retrofit = builder.build()//create retrofit instance

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}