package com.boilerplate.common.objects

import androidx.annotation.IntDef

@IntDef(CodeIntent.UPLOAD_IMAGE)
annotation class CodeIntent {
    companion object {
        const val UPLOAD_IMAGE = 1
        const val APP_SETTINGS = 2
    }

}