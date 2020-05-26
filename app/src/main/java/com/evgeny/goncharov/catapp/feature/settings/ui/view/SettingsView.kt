package com.evgeny.goncharov.catapp.feature.settings.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.feature.settings.di.SettingsSubcomponent
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel
import com.evgeny.goncharov.catapp.feature.settings.ui.SettingsFragment
import kotlinx.android.synthetic.main.fragment_cat_description.view.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import javax.inject.Inject

class SettingsView : ConstraintLayout {

    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, @StyleRes style: Int) : super(
        context,
        attr,
        style
    )

    @Inject
    lateinit var fragment: SettingsFragment


    fun init() {
        SettingsSubcomponent.component?.inject(this)
        initUi()
    }


    fun setThemeModel(value: ThemeModel) {
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
                fragment.onLight()
                initLightTheme()
            } else {
                fragment.onNight()
                initNightTheme()
            }
        }
    }


    private fun initUi() {
        initToolbar()
        initButtonDone()
    }


    private fun initButtonDone() {
        btnUpdateSettings.setOnClickListener {
            fragment.clickButtonDone()
        }
    }


    private fun initToolbar() {
        (toolbar as Toolbar).apply {
            setNavigationIcon(R.drawable.ic_arrow_back_black)
            setNavigationOnClickListener {
                fragment.clickBack()
            }
            setTitle(R.string.settings_to_cat_title)
        }
    }
}