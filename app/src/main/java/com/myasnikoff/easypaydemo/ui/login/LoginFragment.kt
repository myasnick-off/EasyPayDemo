package com.myasnikoff.easypaydemo.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.myasnikoff.easypaydemo.R
import com.myasnikoff.easypaydemo.databinding.FragmentLoginBinding
import com.myasnikoff.easypaydemo.ui.NavigationProvider
import com.myasnikoff.easypaydemo.ui.payments.PaymentsFragment
import com.myasnikoff.easypaydemo.utils.clearErrorMessage
import com.myasnikoff.easypaydemo.utils.hideSoftKeyboard
import com.myasnikoff.easypaydemo.utils.setErrorMessage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    private val viewModel: LoginViewModel by viewModels()

    private var navigationProvider: NavigationProvider? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationProvider = context as? NavigationProvider
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    override fun onDetach() {
        navigationProvider = null
        super.onDetach()
    }

    private fun initView() = with(binding) {
        loginEditText.doAfterTextChanged {
            loginInputLayout.clearErrorMessage()
        }
        passwordEditText.doAfterTextChanged {
            passwordInputLayout.clearErrorMessage()
        }
        loginButton.setOnClickListener {
            root.hideSoftKeyboard()
            val login = binding.loginEditText.text
            val pass = binding.passwordEditText.text
            viewModel.login(login, pass)
        }
        root.setOnClickListener { it.hideSoftKeyboard() }
    }

    private fun initViewModel() {
        viewModel.authStateFlow.onEach(::handleState).launchIn(lifecycleScope)
    }

    private fun handleState(state: AuthState) {
        when (state) {
            AuthState.Initial -> renderInitial()
            AuthState.Loading -> renderLoading()
            AuthState.Success -> navigateToPaymentsScreen()
            AuthState.AuthFailure -> renderAuthFailure()
            AuthState.UnknownFailure -> renderUnknownFailure()
            is AuthState.FieldFailure -> renderFieldFailure(state)
        }
    }

    private fun navigateToPaymentsScreen() {
        navigationProvider?.navigateTo(PaymentsFragment())
    }

    private fun renderInitial() = with(binding) {
        loginLoader.isVisible = false
        errorMessage.isVisible = false
        loginInputLayout.isEnabled = true
        passwordInputLayout.isEnabled = true
        loginButton.isEnabled = true
        loginInputLayout.clearErrorMessage()
        passwordInputLayout.clearErrorMessage()
    }

    private fun renderLoading() = with(binding) {
        loginLoader.isVisible = true
        errorMessage.isVisible = false
        loginInputLayout.isEnabled = false
        passwordInputLayout.isEnabled = false
        loginButton.isEnabled = false
    }

    private fun renderAuthFailure() = with(binding) {
        loginLoader.isVisible = false
        errorMessage.isVisible = true
        errorMessage.setText(R.string.auth_error_message)
        loginInputLayout.isEnabled = true
        passwordInputLayout.isEnabled = true
        loginInputLayout.clearErrorMessage()
        passwordInputLayout.clearErrorMessage()
        loginButton.isEnabled = true
    }

    private fun renderUnknownFailure() = with(binding) {
        loginLoader.isVisible = false
        errorMessage.isVisible = true
        errorMessage.setText(R.string.unknown_error_message)
        loginInputLayout.isEnabled = true
        passwordInputLayout.isEnabled = true
        loginInputLayout.clearErrorMessage()
        passwordInputLayout.clearErrorMessage()
        loginButton.isEnabled = true
    }

    private fun renderFieldFailure(state: AuthState.FieldFailure) = with(binding) {
        loginLoader.isVisible = false
        errorMessage.isVisible = false
        loginInputLayout.setErrorMessage(state.isEmptyName, R.string.name_is_required)
        passwordInputLayout.setErrorMessage(state.isEmptyPass, R.string.pass_is_required)
        loginButton.isEnabled = true
    }
}
