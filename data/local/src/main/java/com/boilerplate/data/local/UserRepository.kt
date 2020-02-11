package com.boilerplate.data.local

import android.content.Context
import com.google.gson.Gson
import com.boilerplate.data.local.base.BaseData
import com.boilerplate.data.model.data.User

class UserRepository(gson: Gson, ctx: Context): BaseData(gson, ctx) {
    companion object {
        private const val NAME = "cache"
        private const val KEY_USERDATA = "usr"
    }

    override fun name() = NAME

    var user: User? = null
        get() {
            if(field == null) field = getData(KEY_USERDATA, User::class.java)
            return field
        }
        set(value) {
            field = value
            if(null == value) clearData(KEY_USERDATA)
            else saveData(KEY_USERDATA, value)
        }

}