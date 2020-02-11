package com.boilerplate.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope

abstract class BaseFragment : androidx.fragment.app.Fragment(), BaseContract.View {

    private val compositeDisposable:CompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if(getLayout() != 0)
            inflater.inflate(getLayout(),container,false)
        else
            null
    }

    fun addUiSubscription(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    fun clearAllSubscription(){
        compositeDisposable.clear()
    }

    fun getBaseActivity(): BaseActivity? =
        if (activity is BaseActivity) {
            activity as BaseActivity
        } else null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSubscription()
        internalSetup()
        setup()
    }

    open fun internalSetup(){}

    //addUiSubscription(RxBus.listen(ClassName::class.java).subscribe{})
    open fun initSubscription() {}

    override fun onDestroyView() {
        super.onDestroyView()
        clearAllSubscription()
    }

    abstract fun setup()
    abstract fun getLayout():Int

    override fun showLoading() {
        (activity as? BaseActivity)?.showLoading()
    }

    override fun dismissLoading() {
        (activity as? BaseActivity)?.dismissLoading()
    }

    override fun errorScreen(message: String?, code: Int?) {
        (activity as? BaseActivity)?.errorScreen(message, code)
    }

    override fun errorScreen(message: String?) {
        (activity as? BaseActivity)?.errorScreen(message)
    }

    override fun forceLogout() {
        (activity as? BaseActivity)?.forceLogout()
    }

    fun goToSettings() {
        (activity as? BaseActivity)?.goToSettings()
    }

    override fun getCoroutine(): CoroutineScope = viewLifecycleOwner.lifecycleScope

}