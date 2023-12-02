package com.myasnikoff.easypaydemo.login.ui

import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.myasnikoff.easypaydemo.R
import com.myasnikoff.easypaydemo.core.ui.BaseFragment
import com.myasnikoff.easypaydemo.core.utils.clearErrorMessage
import com.myasnikoff.easypaydemo.core.utils.hideSoftKeyboard
import com.myasnikoff.easypaydemo.core.utils.setErrorMessage
import com.myasnikoff.easypaydemo.databinding.FragmentLoginBinding
import com.myasnikoff.easypaydemo.login.ui.model.AuthState
import com.myasnikoff.easypaydemo.payments.ui.PaymentsFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    override val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    override val viewModel: LoginViewModel by viewModel()

    override fun initView() = with(binding) {
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

    override fun initViewModel() {
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
