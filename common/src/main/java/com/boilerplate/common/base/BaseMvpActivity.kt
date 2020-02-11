package com.boilerplate.common.base

abstract class BaseMvpActivity<T: BaseContract.Presenter<*>>: BaseActivity() {
    protected abstract val presenter: T

    override fun internalSetup() {
        initPresenterView()
        super.internalSetup()
    }

    abstract fun initPresenterView()

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun forceLogout() {
        presenter.forceLogout()
        super.forceLogout()
    }
}