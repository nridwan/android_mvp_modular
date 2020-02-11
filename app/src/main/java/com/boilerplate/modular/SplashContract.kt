package com.boilerplate.modular

import com.boilerplate.common.base.BaseContract

interface SplashContract {
    interface View: BaseContract.View
    interface Presenter: BaseContract.Presenter<View> {
        fun isLoggedIn(): Boolean
    }
}