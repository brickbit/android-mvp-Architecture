package com.sngular.data.datasource.network

import com.sngular.data.BuildConfig
import com.sngular.data.constants.Constant
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

inline fun <reified S> createService(endPoint: String): S {

    var retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(endPoint)
    val certificatePinner = CertificatePinner.Builder()
        .add(
            Constant.BASE_URL,
            Constant.PINNER_SHA
        ).build()
    if (BuildConfig.DEBUG) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .readTimeout(50, TimeUnit.SECONDS)
            .connectTimeout(50, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        retrofitBuilder = retrofitBuilder.client(client)
    } else {
        val client = OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .readTimeout(50, TimeUnit.SECONDS)
            .connectTimeout(50, TimeUnit.SECONDS)
            .build()

        retrofitBuilder = retrofitBuilder.client(client)
    }

    val retrofit = retrofitBuilder.build()

    return retrofit.create(S::class.java)
}