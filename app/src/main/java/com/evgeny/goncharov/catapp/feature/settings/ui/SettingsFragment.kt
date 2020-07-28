package com.evgeny.goncharov.catapp.feature.settings.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.text.set
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
        initToolbar()
        initClickThemeApp()
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
    }


    private fun initNightTheme() {
        val themeTitle = requireActivity().getString(R.string.theme_title_settings)
        val darkTheme = requireActivity().getString(R.string.settings_night_title)
        val resultTitle = SpannableString("$themeTitle\n$darkTheme")
        resultTitle[0..themeTitle.length] = ForegroundColorSpan(
            ContextCompat.getColor(requireContext(), R.color.white)
        )
        resultTitle[themeTitle.length..themeTitle.length + darkTheme.length] = ForegroundColorSpan(
            ContextCompat.getColor(requireContext(), R.color.white_hint)
        )
        txvThemeApp.text = resultTitle
    }


    private fun initLightTheme() {
        val themeTitle = requireActivity().getString(R.string.theme_title_settings)
        val darkTheme = requireActivity().getString(R.string.settings_light_title)
        val resultTitle = SpannableString("$themeTitle\n$darkTheme")
        resultTitle[0..themeTitle.length] = ForegroundColorSpan(
            ContextCompat.getColor(requireContext(), R.color.text_toolbar_title_light)
        )
        resultTitle[themeTitle.length..themeTitle.length + darkTheme.length + 1] =
            ForegroundColorSpan(
                ContextCompat.getColor(requireContext(), R.color.color_dark_grey)
            )
        txvThemeApp.text = resultTitle
    }


    private fun initClickThemeApp() {
        txvThemeApp.setOnClickListener {
            val dialog = DialogChooseThemeApp()
            dialog.show(requireFragmentManager(), DialogChooseThemeApp::class.java.name)
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