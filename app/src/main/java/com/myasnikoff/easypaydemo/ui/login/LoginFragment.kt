package com.myasnikoff.easypaydemo.ui.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.myasnikoff.easypaydemo.R
import com.myasnikoff.easypaydemo.databinding.FragmentLoginBinding

class LoginFragment: Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    private val viewModel: LoginViewModel by viewModels()
}