package com.boilerplate.common.base

import android.app.Application
import android.content.Context
import android.content.Intent

abstract class BaseApplication: Application() {
    abstract fun getStartingIntent(context: Context): Intent
}