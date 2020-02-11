package com.boilerplate.modular.di

import com.boilerplate.modular.SplashPresenter
import org.koin.dsl.module

val splashModule = module {
    factory { SplashPresenter() }
}