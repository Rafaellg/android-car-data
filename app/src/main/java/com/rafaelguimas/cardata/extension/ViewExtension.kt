package com.rafaelguimas.cardata.extension

import android.view.View

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.changeVisibility(visible: Boolean) {
    if (visible) {
        show()
    } else {
        hide()
    }
}