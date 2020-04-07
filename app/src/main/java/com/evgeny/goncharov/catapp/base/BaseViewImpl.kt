package com.evgeny.goncharov.catapp.base

import android.view.View
import kotlinx.android.synthetic.main.fragment_wall_cats.view.*
import com.evgeny.goncharov.catapp.extension.setVisibilityBool

abstract class BaseViewImpl : IBaseView {

    protected var content: View? = null

    override fun attachView(view: View) {
        content = view
    }

    protected abstract fun initLiveData()


    override fun showProgress() {
        content?.prgLoad?.setVisibilityBool(true)
    }


    override fun hideProgress() {
        content?.prgLoad?.setVisibilityBool(false)
    }
}

