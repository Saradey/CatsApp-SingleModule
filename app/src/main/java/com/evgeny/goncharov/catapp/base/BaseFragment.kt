package com.evgeny.goncharov.catapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.evgeny.goncharov.catapp.extension.setVisibilityBool
import kotlinx.android.synthetic.main.fragment_cat_description.*
import kotlinx.android.synthetic.main.fragment_cat_description.grpStubWallCat
import kotlinx.android.synthetic.main.fragment_cat_description.prgLoad
import kotlinx.android.synthetic.main.fragment_wall_cats.*

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }


    protected abstract fun getLayoutId(): Int

    protected fun showProgress() {
        prgLoad?.setVisibilityBool(true)
    }


    protected fun hideProgress() {
        prgLoad?.setVisibilityBool(false)
    }


    protected fun showStubSomethingWrong() {
        prgLoad?.setVisibilityBool(false)
        grpStubWallCat?.setVisibilityBool(true)
    }


    protected fun hideStubSomethingWrong() {
        grpStubWallCat?.setVisibilityBool(false)
    }
}