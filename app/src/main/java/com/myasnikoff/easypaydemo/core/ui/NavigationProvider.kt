package com.myasnikoff.easypaydemo.core.ui

import androidx.fragment.app.Fragment

interface NavigationProvider {
    fun navigateTo(fragment: Fragment)
}