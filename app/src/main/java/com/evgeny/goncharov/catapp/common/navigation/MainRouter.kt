package com.evgeny.goncharov.catapp.common.navigation

interface MainRouter {

    fun showSlashScreen()

    fun onBackPressed()

    fun showCatDescription(idCat: String)

    fun goToTheSearchCatFragment()

    fun goToTheSettingFragment()

}