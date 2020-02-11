package com.boilerplate.data.remote.di

import com.boilerplate.data.remote.UserService
import com.boilerplate.data.remote.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createRemoteModule(debug: Boolean, baseUrl: String) = module {

    factory<HttpLoggingInterceptor> {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    factory {
        AuthInterceptor(get())
    }

    factory {
        val builder = OkHttpClient.Builder()
        if(debug) builder.addInterceptor(get<HttpLoggingInterceptor>())
        builder
    }

    single {
        val builder = get<OkHttpClient.Builder>()
        Retrofit.Builder()
            .client(builder.build())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single(named("auth")) {
        val builder = get<OkHttpClient.Builder>()
        builder.addInterceptor(get<AuthInterceptor>())
        Retrofit.Builder()
            .client(builder.build())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single { get<Retrofit>().create(UserService::class.java) }

}