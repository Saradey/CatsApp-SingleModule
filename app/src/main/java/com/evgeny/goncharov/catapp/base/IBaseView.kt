package com.evgeny.goncharov.catapp.base

import android.view.View

interface IBaseView {

    fun attachView(view: View)

    fun init()

    fun showProgress()

    fun hideProgress()

    fun showStubSomethingWrong()

}