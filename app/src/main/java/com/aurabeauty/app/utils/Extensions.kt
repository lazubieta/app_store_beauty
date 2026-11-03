package com.aurabeauty.app.utils

import android.content.Context
import android.widget.Toast

fun Double.toCurrencyCOP(): String {
    return "$ %,.0f".format(this).replace(',', '.')
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
