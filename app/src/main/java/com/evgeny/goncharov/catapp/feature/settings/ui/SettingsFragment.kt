package com.evgeny.goncharov.catapp.feature.settings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseFragment
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import com.evgeny.goncharov.catapp.feature.settings.di.SettingsSubcomponent
import com.evgeny.goncharov.catapp.feature.settings.view.model.ISettingsViewModel
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject


class SettingsFragment : BaseFragment() {


    companion object {
        fun getInstance() = SettingsFragment()
    }

    @Inject
    lateinit var factory: SettingsSubcomponent.Factory

    @Inject
    lateinit var viewModel: ISettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivitySubcomponent.component.inject(this)
        SettingsSubcomponent.component = factory.plus()
        init()
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_settings
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
    }


    private fun initUi() {
        mySettingsView.initButtonDone(::clickButtonDone)
        mySettingsView.initToolbar(::clickBack)
    }


    private fun init() {
        initLiveData()
        viewModel.initInjection()
        viewModel.initThemeToView()
    }


    private fun initLiveData() {
        viewModel.getThemeLiveData().observe(this, Observer {
            mySettingsView.setThemeModel(it, ::onLight, ::onNight)
        })
    }


    private fun clickBack() {
        viewModel.clickBack()
    }


    private fun onLight() {
        viewModel.onLight()
    }


    private fun onNight() {
        viewModel.onNight()
    }


    private fun clickButtonDone() {
        viewModel.clickButtonDone()
    }


    override fun onDestroy() {
        super.onDestroy()
        SettingsSubcomponent.component = null
    }
}