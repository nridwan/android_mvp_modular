package com.boilerplate.common.base

abstract class BaseMvpFragment<T: BaseContract.Presenter<*>>: BaseFragment() {
    protected abstract val presenter: T

    override fun internalSetup() {
        initPresenterView()
        super.internalSetup()
    }

    abstract fun initPresenterView()

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }
}