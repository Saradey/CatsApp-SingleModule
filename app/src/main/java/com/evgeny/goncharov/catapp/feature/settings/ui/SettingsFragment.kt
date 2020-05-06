package com.evgeny.goncharov.catapp.feature.settings.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseFragment
import com.evgeny.goncharov.catapp.feature.settings.di.SettingsSubcomponent
import com.evgeny.goncharov.catapp.feature.settings.ui.view.ISettingsView
import com.evgeny.goncharov.catapp.feature.settings.ui.view.SettingsViewImpl
import com.evgeny.goncharov.catapp.feature.settings.view.model.ISettingsViewModel
import javax.inject.Inject

class SettingsFragment : BaseFragment<ISettingsView>() {


    companion object {
        fun getInstance() = SettingsFragment()
        lateinit var component: SettingsSubcomponent
    }

    @Inject
    lateinit var factory: SettingsSubcomponent.Factory

    @Inject
    lateinit var viewModel: ISettingsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivity.component.inject(this)
        component = factory.plus()
    }


    override fun getLayoutId(): Int = R.layout.fragment_settings


    override fun init(content: View) {
        initView(content)
        initLiveData()
        viewModel.initInjection()
        view.init()
        viewModel.initThemeToView()
    }


    private fun initLiveData() {
        viewModel.getThemeLiveData().observe(this, Observer {
            view.setThemeModel(it)
        })
    }


    override fun initView(content: View) {
        view = SettingsViewImpl()
        view.attachView(content)
    }


    fun clickBack() {
        viewModel.clickBack()
    }


    fun onLight() {
        viewModel.onLight()
    }


    fun onNight() {
        viewModel.onNight()
    }


    fun clickButtonDone() {
        viewModel.clickButtonDone()
    }

}