package com.evgeny.goncharov.catapp.feature.settings.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.feature.settings.di.SettingsSubcomponent
import com.evgeny.goncharov.catapp.feature.settings.view.model.ISettingsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class DialogChooseLanguageApp : DialogFragment() {

    companion object {
        const val INDEX_CHOOSE_RU = 0
        const val INDEX_CHOOSE_EN = 1
    }

    @Inject
    lateinit var vm: ISettingsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingsSubcomponent.component?.inject(this)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builderDialog = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.Costume_ThemeMaterialAlertDialog
        )
        builderDialog.setTitle(R.string.language_app_title)
            .setPositiveButton(
                R.string.title_cancel_dialog_common
            ) { _, _ ->
                dismiss()
            }
            .setSingleChoiceItems(
                resources.getStringArray(R.array.language_titles),
                vm.getSelectLanguage()
            ) { _, item ->
                vm.chooseLanguage(item)
                dismiss()
            }
        return builderDialog.create()
    }


}