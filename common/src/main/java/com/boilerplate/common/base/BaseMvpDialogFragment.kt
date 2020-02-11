package com.boilerplate.common.base

abstract class BaseMvpDialogFragment<T: BaseContract.Presenter<*>>: BaseDialogFragment() {
    protected abstract val presenter: T

    override fun internalSetup() {
        initPresenterView()
        super.internalSetup()
    }

    abstract fun initPresenterView()

    override fun onDestroyView() {
        super.onDestroyView()
    }
}