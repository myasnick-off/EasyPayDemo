package com.myasnikoff.easypaydemo.ui.main

import androidx.fragment.app.Fragment

interface NavigationProvider {
    fun navigateTo(fragment: Fragment)
}