package com.evgeny.goncharov.catapp.feature.settings.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseFragment
import com.evgeny.goncharov.catapp.di.components.ActivitySubcomponent
import com.evgeny.goncharov.catapp.feature.settings.di.SettingsSubcomponent
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel
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


    override fun getLayoutId(): Int = R.layout.fragment_settings


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
    }


    private fun initUi() {
        initButtonDone()
        initToolbar()
    }


    private fun init() {
        initLiveData()
        viewModel.initInjection()
        viewModel.initThemeToView()
    }


    private fun initLiveData() {
        viewModel.getThemeLiveData().observe(this, Observer {
            setThemeModel(it)
        })
    }


    private fun setThemeModel(value: ThemeModel) {
        if (value.themeValue == AppCompatDelegate.MODE_NIGHT_NO) {
            initLightTheme()
        } else if (value.themeValue == AppCompatDelegate.MODE_NIGHT_YES) {
            initNightTheme()
        }
        initChekerTheme()
    }


    private fun initNightTheme() {
        txvTitleValueNow.setText(R.string.settings_night_title)
        swtChooseTheme.isChecked = false
    }


    private fun initLightTheme() {
        txvTitleValueNow.setText(R.string.settings_light_title)
        swtChooseTheme.isChecked = true
    }


    private fun initChekerTheme() {
        swtChooseTheme.setOnCheckedChangeListener { _, check ->
            if (check) {
                viewModel.onLight()
                initLightTheme()
            } else {
                viewModel.onNight()
                initNightTheme()
            }
        }
    }


    private fun initButtonDone() {
        btnUpdateSettings.setOnClickListener {
            viewModel.clickButtonDone()
        }
    }


    private fun initToolbar() {
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow_back_black)
            setNavigationOnClickListener {
                viewModel.clickBack()
            }
            setTitle(R.string.settings_to_cat_title)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        SettingsSubcomponent.component = null
    }
}