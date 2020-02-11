package com.boilerplate.features.home.di

import com.boilerplate.features.home.HomePresenter
import org.koin.dsl.module

val featureHomeModule = module {
    factory { HomePresenter(get(), get()) }
}