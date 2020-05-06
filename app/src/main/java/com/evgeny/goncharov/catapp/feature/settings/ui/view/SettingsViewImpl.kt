package com.evgeny.goncharov.catapp.feature.settings.ui.view

import androidx.appcompat.widget.Toolbar
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseViewImpl
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel
import com.evgeny.goncharov.catapp.feature.settings.ui.SettingsFragment
import kotlinx.android.synthetic.main.fragment_cat_description.view.*
import javax.inject.Inject

class SettingsViewImpl : BaseViewImpl(), ISettingsView {

    @Inject
    lateinit var fragment: SettingsFragment


    override fun init() {
        SettingsFragment.component.inject(this)
        initUi()
    }


    override fun setThemeModel(it: ThemeModel) {

    }


    private fun initUi() {
        initToolbar()
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