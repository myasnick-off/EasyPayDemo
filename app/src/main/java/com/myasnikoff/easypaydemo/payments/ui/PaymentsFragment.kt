package com.myasnikoff.easypaydemo.payments.ui

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.myasnikoff.easypaydemo.R
import com.myasnikoff.easypaydemo.core.ui.BaseFragment
import com.myasnikoff.easypaydemo.databinding.FragmentPaymentsBinding
import com.myasnikoff.easypaydemo.login.ui.LoginFragment
import com.myasnikoff.easypaydemo.payments.ui.adapter.PaymentsAdapter
import com.myasnikoff.easypaydemo.payments.ui.model.PaymentItem
import com.myasnikoff.easypaydemo.payments.ui.model.PaymentsState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentsFragment : BaseFragment(R.layout.fragment_payments) {

    override val binding: FragmentPaymentsBinding by viewBinding(FragmentPaymentsBinding::bind)

    override val viewModel: PaymentsViewModel by viewModel()

    private val paymentsAdapter = PaymentsAdapter()

    override fun initMenu() {
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

    override fun initView() = with(binding) {
        contentRecycler.adapter = paymentsAdapter
        emptyResult.retryButton.setOnClickListener { viewModel.getPayments() }
    }

    override fun initViewModel() {
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
