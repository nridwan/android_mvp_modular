package com.boilerplate.common.extension

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

inline fun <reified T: Any> Context.intentFor() = Intent(this, T::class.java)
inline fun <reified T: Any> Fragment.intentFor() = Intent(this.context, T::class.java)

fun Intent.clearTop() = apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) }
fun Intent.newTask() = apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }