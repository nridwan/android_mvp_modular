package com.boilerplate.data.local.base

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

abstract class BaseData(val gson: Gson, ctx: Context) {
    abstract fun name(): String

    protected lateinit var sharePref: SharedPreferences

    protected val sharePrefEditor: SharedPreferences.Editor by lazy { sharePref.edit() }

    init {
        initPreferenceGroup(ctx)
    }

    fun initPreferenceGroup(ctx: Context) {
        sharePref = ctx.getSharedPreferences(name(), Context.MODE_PRIVATE)
    }

    fun <T>getData(key: String, classOfT: Class<T>): T? {
        return try {
            val text = sharePref.getString(key, null)
            gson.fromJson(text, classOfT)
        } catch (_: Exception) {
            clearData(key)
            null
        }
    }

    fun <T>saveData(key: String, obj: T) {
        sharePrefEditor.putString(key, gson.toJson(obj))
        sharePrefEditor.commit()
    }

    fun clearData(key: String) {
        sharePrefEditor.remove(key)?.commit()
    }

}