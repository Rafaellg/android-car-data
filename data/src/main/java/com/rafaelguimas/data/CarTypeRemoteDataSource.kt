package com.rafaelguimas.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class CarTypeRemoteDataSource {
    companion object {
        private const val BASE_URL = "http://api-aws-eu-qa-1.auto1-test.com"
    }

    private val retrofit: Retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()

                val url = original
                    .url()
                    .newBuilder()
                    .addQueryParameter("wa_key", "coding-puzzle-client-449cc9d")
                    .build()

                val request = original
                    .newBuilder()
                    .header("Content-Type", "application/json")
                    .url(url)
                    .build()

                return chain.proceed(request)
            }
        })

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    suspend fun getManufacturer(pageSize: Int, page: Int) = withContext(Dispatchers.IO) {
        retrofit.create(CarTypesApi::class.java).getManufacturer(pageSize, page)
    }

    suspend fun getMainTypes(manufacturer: Int, pageSize: Int, page: Int) = withContext(Dispatchers.IO) {
        retrofit.create(CarTypesApi::class.java).getMainTypes(manufacturer, pageSize, page)
    }

    suspend fun getBuiltDates(manufacturer: Int, mainType: Int) = withContext(Dispatchers.IO) {
        retrofit.create(CarTypesApi::class.java).getBuiltDates(manufacturer, mainType)
    }
}