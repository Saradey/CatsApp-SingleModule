package com.evgeny.goncharov.catapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected lateinit var view: BaseView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutId(), container)
        init(view)
        return view
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun init(content: View)

    protected abstract fun initBaseView()

    protected abstract fun initViewModel()

    protected fun showProgress() {

    }

    protected fun hideProgress() {

    }
}