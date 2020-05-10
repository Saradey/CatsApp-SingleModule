package com.evgeny.goncharov.catapp.extension

import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat

fun SearchView.setHintTextColor(@ColorRes color: Int) {
    val editText = findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
    editText.setHintTextColor(
        ContextCompat.getColor(
            context,
            color
        )
    )
}

fun SearchView.setTextColor(@ColorRes color: Int) {
    val editText = findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
    editText.setTextColor(
        ContextCompat.getColor(
            context,
            color
        )
    )
}