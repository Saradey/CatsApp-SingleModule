package com.evgeny.goncharov.catapp.common.navigation

interface IMainRouter {

    fun showSlashScreen()

    fun onBackPressed()

    fun showCatDescription(idCat: String)

    fun goToTheSearchCatFragment()

    fun goToTheSettingFragment()

}