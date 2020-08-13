package com.evgeny.goncharov.catapp.feature.settings.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.feature.settings.di.SettingsSubcomponent
import com.evgeny.goncharov.catapp.feature.settings.view.model.SettingsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class DialogChooseThemeApp : DialogFragment() {

    @Inject
    lateinit var vm: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingsSubcomponent.component?.inject(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builderDialog = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.Costume_ThemeMaterialAlertDialog
        )
        builderDialog.setTitle(R.string.theme_title_settings)
            .setPositiveButton(
                R.string.title_cancel_dialog_common
            ) { _, _ ->
                dismiss()
            }
            .setSingleChoiceItems(
                resources.getStringArray(R.array.themes_titles),
                vm.getThemeValue()
            ) { _, item ->
                vm.setChooseThemeIndex(item)
                dismiss()
            }
        return builderDialog.create()
    }
}