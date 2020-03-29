package com.evgeny.goncharov.catapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : IBaseView> : Fragment(), IBaseFragment {

    protected lateinit var view: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutId(), container, false)
        init(view)
        return view
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun init(content: View)

    protected abstract fun initBaseView(content: View)

    protected abstract fun initLiveData()

}