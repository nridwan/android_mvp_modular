package com.boilerplate.modular.di

import com.boilerplate.data.local.di.createRepositoryModule
import com.boilerplate.data.remote.di.createRemoteModule
import com.boilerplate.features.home.di.featureHomeModule
import com.boilerplate.modular.BuildConfig

val appComponent = listOf(
    createRepositoryModule(),
    createRemoteModule(BuildConfig.DEBUG, "https://api.github.com/"),
    splashModule,
    featureHomeModule)