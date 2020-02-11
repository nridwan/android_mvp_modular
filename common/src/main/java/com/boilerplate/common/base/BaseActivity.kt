package com.boilerplate.common.base

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.boilerplate.common.extension.toast
import com.boilerplate.common.objects.CodeIntent
import com.boilerplate.common.ui.component.LoadingProgressDialog
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import java.net.HttpURLConnection


abstract class BaseActivity: AppCompatActivity(), BaseContract.View {

    private val uiCompositeDisposable:CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        initLayout()
        initSubscription()
        internalSetup()
        setup()
    }

    open fun internalSetup(){}

    abstract fun setup()

    abstract fun getLayout():Int

    fun initLayout(){
        if(getLayout() != 0)
            setContentView(getLayout())
    }

    open fun initSubscription() {
    }

    fun addUiSubscription(disposable: Disposable){
        uiCompositeDisposable.add(disposable)
    }

    fun clearAllSubscription(){
        uiCompositeDisposable.clear()
    }

    override fun onDestroy() {
        clearAllSubscription()
        super.onDestroy()
    }

    override fun showLoading() {
        Handler().post {
            supportFragmentManager.executePendingTransactions()
            var progressDialog = supportFragmentManager
                .findFragmentByTag(LoadingProgressDialog::class.java.canonicalName) as LoadingProgressDialog?

            if (progressDialog == null) {
                progressDialog = LoadingProgressDialog.newInstance()
                progressDialog.show(supportFragmentManager, LoadingProgressDialog::class.java.canonicalName)
            }
        }
    }

    override fun dismissLoading() {
        Handler().post {
            supportFragmentManager.executePendingTransactions()
            val progressDialog = supportFragmentManager
                .findFragmentByTag(LoadingProgressDialog::class.java.canonicalName) as LoadingProgressDialog?
            progressDialog?.dismiss()
        }
    }

    override fun errorScreen(message: String?, code: Int?) {
        dismissLoading()
        message?.let { toast(it) }
        if(code == HttpURLConnection.HTTP_UNAUTHORIZED) forceLogout()
    }

    override fun errorScreen(message: String?) {
        dismissLoading()
        message?.let { toast(it) }
    }

    override fun forceLogout() {
        (application as? BaseApplication)?.getStartingIntent(this)?.apply {
            startActivity(this)
        }
        finishAffinity()
    }

    override fun getCoroutine(): CoroutineScope = lifecycleScope

    fun goToSettings() {
        val myAppSettings =
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName"))
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT)
        myAppSettings.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        myAppSettings.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        myAppSettings.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        startActivityForResult(myAppSettings, CodeIntent.APP_SETTINGS)
    }

}