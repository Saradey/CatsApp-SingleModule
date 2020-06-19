package com.evgeny.goncharov.catapp.feature.settings.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeDTO
import kotlinx.android.synthetic.main.fragment_cat_description.view.*
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsView : ConstraintLayout {

    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, @StyleRes style: Int) : super(
        context,
        attr,
        style
    )


    fun setThemeModel(value: ThemeDTO, onLight: () -> Unit, onNight: () -> Unit) {
        if (value.themeValue == AppCompatDelegate.MODE_NIGHT_NO) {
            initLightTheme()
        } else if (value.themeValue == AppCompatDelegate.MODE_NIGHT_YES) {
            initNightTheme()
        }
        initChekerTheme(onLight, onNight)
    }


    private fun initNightTheme() {
        txvTitleValueNow.setText(R.string.settings_night_title)
        swtChooseTheme.isChecked = false
    }


    private fun initLightTheme() {
        txvTitleValueNow.setText(R.string.settings_light_title)
        swtChooseTheme.isChecked = true
    }


    private fun initChekerTheme(onLight: () -> Unit, onNight: () -> Unit) {
        swtChooseTheme.setOnCheckedChangeListener { _, check ->
            if (check) {
                onLight()
                initLightTheme()
            } else {
                onNight()
                initNightTheme()
            }
        }
    }


    fun initButtonDone(clickButtonDone: () -> Unit) {
        btnUpdateSettings.setOnClickListener {
            clickButtonDone()
        }
    }


    fun initToolbar(clickBack: () -> Unit) {
        (toolbar as Toolbar).apply {
            setNavigationIcon(R.drawable.ic_arrow_back_black)
            setNavigationOnClickListener {
                clickBack()
            }
            setTitle(R.string.settings_to_cat_title)
        }
    }
}