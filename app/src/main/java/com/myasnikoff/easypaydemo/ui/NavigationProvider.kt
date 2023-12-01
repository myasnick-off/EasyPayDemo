package com.myasnikoff.easypaydemo.ui

import androidx.fragment.app.Fragment

interface NavigationProvider {
    fun navigateTo(fragment: Fragment)
}