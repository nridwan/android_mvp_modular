package com.boilerplate.common.ui.component

import android.app.Dialog
import com.boilerplate.common.R
import com.boilerplate.common.base.BaseDialogFragment

class LoadingProgressDialog: BaseDialogFragment() {

    companion object {
        fun newInstance(): LoadingProgressDialog {
            val fragment = LoadingProgressDialog()
            return fragment
        }
    }

    override fun setupDialogStyle(dialog: Dialog) {
        if (null != dialog.window && null != dialog.window!!.attributes) {
            dialog.setCancelable(false)
        }
        isCancelable = false
    }

    override fun loadArguments() {
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun setup() {
    }

    override fun getLayout(): Int = R.layout.loading_default
}