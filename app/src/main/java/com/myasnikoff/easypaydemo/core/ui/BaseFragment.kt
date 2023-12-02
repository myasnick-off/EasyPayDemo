package com.myasnikoff.easypaydemo.core.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment(layoutResId: Int) : Fragment(layoutResId) {

    abstract val binding: ViewBinding
    abstract val viewModel: BaseViewModel

    protected var navigationProvider: NavigationProvider? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationProvider = context as? NavigationProvider
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMenu()
        initView()
        initViewModel()
    }

    override fun onDetach() {
        navigationProvider = null
        super.onDetach()
    }

    open fun initMenu() {}

    abstract fun initView()

    abstract fun initViewModel()
}