package com.evgeny.goncharov.catapp.feature.settings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import com.evgeny.goncharov.catapp.feature.settings.di.SettingsSubcomponent
import com.evgeny.goncharov.catapp.feature.settings.ui.view.SettingsView
import com.evgeny.goncharov.catapp.feature.settings.view.model.ISettingsViewModel
import javax.inject.Inject


class SettingsFragment : Fragment() {


    companion object {
        fun getInstance() = SettingsFragment()
    }

    @Inject
    lateinit var factory: SettingsSubcomponent.Factory

    @Inject
    lateinit var viewModel: ISettingsViewModel

    private lateinit var myView: SettingsView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        init(view)
        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivitySubcomponent.component.inject(this)
        SettingsSubcomponent.component = factory.plus()
    }


    fun init(content: View) {
        initView(content)
        initLiveData()
        viewModel.initInjection()
        myView.init()
        viewModel.initThemeToView()
    }


    private fun initLiveData() {
        viewModel.getThemeLiveData().observe(this, Observer {
            myView.setThemeModel(it)
        })
    }


    private fun initView(content: View) {
        myView = SettingsView()
        myView.attachView(content)
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


    override fun onDestroy() {
        super.onDestroy()
        SettingsSubcomponent.component = null
    }
}