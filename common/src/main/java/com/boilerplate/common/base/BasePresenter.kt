package com.boilerplate.common.base

import org.json.JSONObject

open class BasePresenter<T: BaseContract.View>: BaseContract.Presenter<T> {

    override var view: T? = null

    override fun detachView(){
        this.view = null
    }

    override fun getErrorResponse(errorBody: String?, httpErrorMessage: String?): String {
        var message = ""
        errorBody?.let {
            val jsonObject = JSONObject(it)
            message = jsonObject.getString("message")
        }

        if (message.isEmpty()) {
            httpErrorMessage?.let { message = it }
        }
        return message
    }

    override fun forceLogout() {
    }

}