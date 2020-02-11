package com.boilerplate.modular

import com.boilerplate.common.base.BaseMvpActivity
import com.boilerplate.common.extension.intentFor
import com.boilerplate.features.home.HomeActivity
import org.koin.android.ext.android.inject

class SplashActivity: BaseMvpActivity<SplashContract.Presenter>(), SplashContract.View {

    override val presenter: SplashContract.Presenter by inject<SplashPresenter>()

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun setup() {
        startActivity(intentFor<HomeActivity>())
        finishAffinity()
    }

    override fun getLayout(): Int = 0
}