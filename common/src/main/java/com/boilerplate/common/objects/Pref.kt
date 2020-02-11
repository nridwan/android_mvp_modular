package com.boilerplate.common.objects

import androidx.annotation.StringDef

interface Pref {
    @StringDef(Key.Token)
    annotation class Key {
        companion object {
            const val Token = "tk"
            const val Profile = "prof"
        }
    }

    @StringDef(Name.Static)
    annotation class Name {
        companion object {
            const val Static = "pref_stt"
        }
    }
}