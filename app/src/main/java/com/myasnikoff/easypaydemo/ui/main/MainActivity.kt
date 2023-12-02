package com.myasnikoff.easypaydemo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.myasnikoff.easypaydemo.R
import com.myasnikoff.easypaydemo.ui.login.LoginFragment
import com.myasnikoff.easypaydemo.ui.payments.PaymentsFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), NavigationProvider {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
    }

    override fun navigateTo(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.container, fragment)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }

    private fun initViewModel() {
        viewModel.loginStateFlow.onEach(::handleLoginState).launchIn(lifecycleScope)
    }

    private fun handleLoginState(isLoggedIn: Boolean) {
        val initialFragment = if (isLoggedIn) PaymentsFragment() else LoginFragment()
        navigateTo(initialFragment)
    }
}