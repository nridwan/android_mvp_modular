package com.boilerplate.modular

import com.boilerplate.common.base.BasePresenter

class SplashPresenter: BasePresenter<SplashContract.View>(), SplashContract.Presenter {
    override fun isLoggedIn(): Boolean = false
}