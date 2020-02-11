package com.boilerplate.data.local.di

import com.google.gson.Gson
import com.boilerplate.data.local.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun createRepositoryModule() = module {
    single { Gson() }
    single { UserRepository(get(), androidContext()) }
}