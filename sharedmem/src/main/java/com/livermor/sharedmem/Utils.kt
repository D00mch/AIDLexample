package com.livermor.sharedmem

import android.content.Context
import android.widget.Toast

fun Any.TAG(): String = this::class.java.simpleName

fun Context.toast(str: String) {
    Toast.makeText(applicationContext,	str, Toast.LENGTH_SHORT).show()
}
