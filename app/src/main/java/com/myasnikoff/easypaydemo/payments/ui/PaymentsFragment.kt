package com.myasnikoff.easypaydemo.payments.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.myasnikoff.easypaydemo.R
import com.myasnikoff.easypaydemo.databinding.FragmentPaymentsBinding
import com.myasnikoff.easypaydemo.core.ui.NavigationProvider
import com.myasnikoff.easypaydemo.login.ui.LoginFragment
import com.myasnikoff.easypaydemo.payments.ui.adapter.PaymentsAdapter
import com.myasnikoff.easypaydemo.payments.ui.model.PaymentItem
import com.myasnikoff.easypaydemo.payments.ui.model.PaymentsState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentsFragment : Fragment(R.layout.fragment_payments) {

    private val binding: FragmentPaymentsBinding by viewBinding(FragmentPaymentsBinding::bind)

    private val viewModel: PaymentsViewModel by viewModel()

    private var navigationProvider: NavigationProvider? = null
    private val paymentsAdapter = PaymentsAdapter()

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

    private fun initMenu() {
        binding.paymentsToolbar.apply {
            setTitle(R.string.payments)
            setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId == R.id.menu_logout) {
                    viewModel.logout()
                }
                true
            }
        }
    }

    private fun initView() = with(binding) {
        contentRecycler.adapter = paymentsAdapter
        emptyResult.retryButton.setOnClickListener { viewModel.getPayments() }
    }

    private fun initViewModel() {
        viewModel.paymentsStateFlow.onEach(::handleState).launchIn(lifecycleScope)
    }

    private fun handleState(state: PaymentsState) {
        when (state) {
            PaymentsState.Loading -> renderLoading()
            PaymentsState.EmptyData -> renderEmptyData()
            is PaymentsState.ValidData -> renderValidData(state.data)
            PaymentsState.UnknownFailure -> renderUnknownFailure()
            PaymentsState.Logout -> navigateToAuthScreen()
        }
    }

    private fun navigateToAuthScreen() {
        navigationProvider?.navigateTo(LoginFragment())
    }

    private fun renderLoading() = with(binding) {
        loader.isVisible = true
        error.isVisible = false
        emptyResult.root.isVisible = false
    }

    private fun renderEmptyData() = with(binding) {
        loader.isVisible = false
        error.isVisible = false
        emptyResult.root.isVisible = true
        contentRecycler.isVisible = false
    }

    private fun renderUnknownFailure() = with(binding) {
        loader.isVisible = false
        error.isVisible = true
        emptyResult.root.isVisible = false
        contentRecycler.isVisible = false
    }

    private fun renderValidData(payments: List<PaymentItem>) = with(binding) {
        loader.isVisible = false
        error.isVisible = false
        emptyResult.root.isVisible = false
        contentRecycler.isVisible = true
        paymentsAdapter.submitList(payments)
    }
}
