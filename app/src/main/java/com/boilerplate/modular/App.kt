package com.boilerplate.modular

import android.content.Context
import com.boilerplate.common.base.BaseApplication
import com.boilerplate.common.extension.clearTop
import com.boilerplate.common.extension.intentFor
import com.boilerplate.common.extension.newTask
import com.boilerplate.modular.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    // CONFIGURATION ---
    fun configureDi() =
        startKoin {
            androidContext(this@App)
            modules(provideComponent())
        }

    // PUBLIC API ---
    fun provideComponent() = appComponent

    override fun getStartingIntent(context: Context) = context.intentFor<SplashActivity>().clearTop().newTask()
}