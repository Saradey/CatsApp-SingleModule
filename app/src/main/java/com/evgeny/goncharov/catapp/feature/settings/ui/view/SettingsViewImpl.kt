package com.evgeny.goncharov.catapp.feature.settings.ui.view

import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseViewImpl
import com.evgeny.goncharov.catapp.feature.settings.di.SettingsSubcomponent
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel
import com.evgeny.goncharov.catapp.feature.settings.ui.SettingsFragment
import kotlinx.android.synthetic.main.fragment_cat_description.view.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import javax.inject.Inject

class SettingsViewImpl : BaseViewImpl(), ISettingsView {

    @Inject
    lateinit var fragment: SettingsFragment


    override fun init() {
        SettingsSubcomponent.component?.inject(this)
        initUi()
    }


    override fun setThemeModel(value: ThemeModel) {
        if (value.themeValue == AppCompatDelegate.MODE_NIGHT_NO) {
            initLightTheme()
        } else if (value.themeValue == AppCompatDelegate.MODE_NIGHT_YES) {
            initNightTheme()
        }
        initChekerTheme()
    }


    private fun initNightTheme() {
        content?.apply {
            txvTitleValueNow.setText(R.string.settings_night_title)
            swtChooseTheme.isChecked = false
        }
    }


    private fun initLightTheme() {
        content?.apply {
            txvTitleValueNow.setText(R.string.settings_light_title)
            swtChooseTheme.isChecked = true
        }
    }


    private fun initChekerTheme() {
        content?.apply {
            swtChooseTheme.setOnCheckedChangeListener { _, check ->
                if (check) {
                    fragment.onLight()
                    initLightTheme()
                } else {
                    fragment.onNight()
                    initNightTheme()
                }
            }
        }
    }


    private fun initUi() {
        initToolbar()
        initButtonDone()
    }


    private fun initButtonDone() {
        content?.apply {
            btnUpdateSettings.setOnClickListener {
                fragment.clickButtonDone()
            }
        }
    }


    private fun initToolbar() {
        content?.apply {
            (toolbar as Toolbar).apply {
                setNavigationIcon(R.drawable.ic_arrow_back_black)
                setNavigationOnClickListener {
                    fragment.clickBack()
                }
                setTitle(R.string.settings_to_cat_title)
            }
        }
    }
}