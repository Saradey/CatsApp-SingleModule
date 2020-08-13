package com.evgeny.goncharov.catapp.extension

import android.view.View

fun View.setVisibilityBool(vis: Boolean) {
    visibility = if (vis) {
        View.VISIBLE
    } else {
        View.GONE
    }
}