package com.studycode.recyclerview.service

import com.google.gson.GsonBuilder
import com.studycode.recyclerview.service.responses.AllcasesResponse
import com.studycode.recyclerview.service.responses.Countries
import com.studycode.recyclerview.service.responses.CountriesItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

interface Api {
    @GET("all")
     suspend fun getAll():Response<AllcasesResponse>

    @GET("countries")
    suspend fun allcountries():Response<List<CountriesItem>>

    companion object{
        operator fun invoke():Api{

            return Retrofit.Builder()
                .addConverterFactory(ApiWorker.gsonConverter)
                .baseUrl("https://corona.lmao.ninja/")
                .client(ApiWorker.client)
                .build()
                .create(Api::class.java)
        }
    }

    object ApiWorker {
        private var mClient: OkHttpClient? = null
        private var mGsonConverter: GsonConverterFactory? = null
        val client: OkHttpClient
            @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
            get() {
                if (mClient == null) {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BODY
                    val httpBuilder = OkHttpClient.Builder()
                    httpBuilder
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .addInterceptor(interceptor) // show all JSON in logCat
                    mClient = httpBuilder.build()
                }
                return mClient!!
            }
        val gsonConverter: GsonConverterFactory
            get() {
                if (mGsonConverter == null) {
                    mGsonConverter = GsonConverterFactory
                        .create(
                            GsonBuilder()
                                .setLenient()
                                .disableHtmlEscaping()
                                .create()
                        )
                }
                return mGsonConverter!!
            }
    }
}