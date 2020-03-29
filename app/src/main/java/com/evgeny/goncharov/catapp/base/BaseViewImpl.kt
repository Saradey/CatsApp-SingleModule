package com.evgeny.goncharov.catapp.base

import android.view.View

abstract class BaseViewImpl : IBaseView {

    protected var content: View? = null

    override fun attachView(view: View) {
        content = view
    }


}