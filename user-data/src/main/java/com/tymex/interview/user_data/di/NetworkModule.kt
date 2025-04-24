package com.tymex.interview.user_data.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tymex.interview.core.network.DefaultHeadersInterceptor
import com.tymex.interview.user_data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        provideRetrofit(
            provideMoshi(),
            provideOkHttpClient(),
        ).create(com.tymex.interview.user_data.remote.ApiService::class.java)
    }
}

private fun provideOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(DefaultHeadersInterceptor())
        .build()
}

inline fun <reified T> T.toJson(): String {
    val jsonAdapter = provideMoshi().adapter(T::class.java)
    return jsonAdapter.toJson(this)
}

fun provideMoshi(): Moshi =
    Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private fun provideRetrofit(
    moshi: Moshi,
    okHttpClient: OkHttpClient,
): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()