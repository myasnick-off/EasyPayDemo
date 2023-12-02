package com.myasnikoff.easypaydemo.payments.ui.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.myasnikoff.easypaydemo.databinding.ItemPaymentBinding
import com.myasnikoff.easypaydemo.payments.ui.model.PaymentItem

class PaymentViewHolder(private val binding: ItemPaymentBinding) : ViewHolder(binding.root) {

    fun bind(item: PaymentItem) = with(binding) {
        paymentTitle.text = item.title
        paymentDate.text = item.dateString
        paymentAmount.text = item.amountString
    }
}
