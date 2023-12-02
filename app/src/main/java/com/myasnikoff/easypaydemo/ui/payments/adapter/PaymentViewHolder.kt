package com.myasnikoff.easypaydemo.ui.payments.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.myasnikoff.easypaydemo.databinding.ItemPaymentBinding
import com.myasnikoff.easypaydemo.ui.payments.PaymentItem

class PaymentViewHolder(private val binding: ItemPaymentBinding) : ViewHolder(binding.root) {

    fun bind(item: PaymentItem) = with(binding) {
        paymentTitle.text = item.title
        paymentDate.text = item.dateString
        paymentAmount.text = item.amountString
    }
}
