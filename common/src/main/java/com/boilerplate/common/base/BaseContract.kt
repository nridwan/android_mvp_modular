package com.boilerplate.common.base

import kotlinx.coroutines.CoroutineScope

interface BaseContract {
    interface View {
        fun showLoading()
        fun dismissLoading()
        fun errorScreen(message: String? = "", code: Int? = -1)
        fun errorScreen(message: String? = "")
        fun forceLogout()
        fun getCoroutine(): CoroutineScope
    }

    interface Presenter<T> {
        var view: T?
        fun detachView()
        fun getErrorResponse(errorBody: String?, httpErrorMessage: String?): String
        fun forceLogout()
    }
}