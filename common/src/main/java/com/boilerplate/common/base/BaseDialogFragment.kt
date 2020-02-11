package com.boilerplate.common.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseDialogFragment : androidx.fragment.app.DialogFragment() {

    private val compositeDisposable:CompositeDisposable = CompositeDisposable()

    fun addUiSubscription(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    fun clearAllSubscription(){
        compositeDisposable.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadArguments()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        setupDialogStyle(dialog)
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (0 != getLayout()) {
            inflater.inflate(getLayout(), container, false)
        } else super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        injectView()
        super.onViewCreated(view, savedInstanceState)
        initSubscription()
        internalSetup()
        setup()
    }

    open fun internalSetup(){}

    abstract fun setupDialogStyle(dialog: Dialog)

    abstract fun loadArguments()

    open fun injectView(){}

    //addUiSubscription(RxBus.listen(ClassName::class.java).subscribe{})
    open fun initSubscription() {}

    override fun onDestroyView() {
        super.onDestroyView()
        clearAllSubscription()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            super.show(manager, tag);
        } catch (ignored: IllegalStateException) {

        }
    }

    override fun dismiss() {
        if (isStateSaved) {
            dismissAllowingStateLoss()
        } else {
            super.dismiss()
        }
    }

    fun goToSettings() {
        (activity as? BaseActivity)?.goToSettings()
    }

    abstract fun setup()
    abstract fun getLayout():Int
}
