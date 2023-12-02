package com.myasnikoff.easypaydemo.payments.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.myasnikoff.easypaydemo.databinding.ItemPaymentBinding
import com.myasnikoff.easypaydemo.payments.ui.model.PaymentItem

class PaymentsAdapter(
    diffUtilCallBack: DiffUtil.ItemCallback<PaymentItem> = PaymentsDiffUtilCallback()
) : ListAdapter<PaymentItem, PaymentViewHolder>(diffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val binding = ItemPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PaymentsDiffUtilCallback : DiffUtil.ItemCallback<PaymentItem>() {
    override fun areItemsTheSame(oldItem: PaymentItem, newItem: PaymentItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PaymentItem, newItem: PaymentItem): Boolean =
        oldItem == newItem
}
