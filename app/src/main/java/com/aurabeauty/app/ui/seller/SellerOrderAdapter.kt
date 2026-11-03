package com.aurabeauty.app.ui.seller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aurabeauty.app.R
import com.aurabeauty.app.data.model.Order
import com.aurabeauty.app.databinding.ItemSellerOrderBinding

class SellerOrderAdapter(
    private var orders: List<Order>,
    private val onOrderDetailClick: (Order) -> Unit
) : RecyclerView.Adapter<SellerOrderAdapter.SellerOrderViewHolder>() {

    inner class SellerOrderViewHolder(private val binding: ItemSellerOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            val context = binding.root.context
            binding.tvSellerOrderId.text = order.orderNumber
            binding.tvSellerOrderDate.text = order.date
            binding.tvSellerOrderItemsCount.text = context.getString(R.string.label_products_count, order.itemsCount)
            binding.tvSellerOrderClient.text = "${context.getString(R.string.label_client)}${order.clientName}"

            // Badge styling
            binding.tvSellerOrderStatusBadge.text = order.getStatusLabel()
            when (order.status.lowercase()) {
                "pendiente" -> {
                    binding.tvSellerOrderStatusBadge.background = ContextCompat.getDrawable(context, R.drawable.bg_badge_pending)
                    binding.tvSellerOrderStatusBadge.setTextColor(ContextCompat.getColor(context, R.color.status_pending_text))
                }
                "en_preparacion" -> {
                    binding.tvSellerOrderStatusBadge.background = ContextCompat.getDrawable(context, R.drawable.bg_badge_preparing)
                    binding.tvSellerOrderStatusBadge.setTextColor(ContextCompat.getColor(context, R.color.status_preparing_text))
                }
                "enviado" -> {
                    binding.tvSellerOrderStatusBadge.background = ContextCompat.getDrawable(context, R.drawable.bg_badge_shipped)
                    binding.tvSellerOrderStatusBadge.setTextColor(ContextCompat.getColor(context, R.color.status_shipped_text))
                }
                else -> {
                    binding.tvSellerOrderStatusBadge.background = ContextCompat.getDrawable(context, R.drawable.bg_badge_active)
                    binding.tvSellerOrderStatusBadge.setTextColor(ContextCompat.getColor(context, R.color.status_active_text))
                }
            }

            binding.btnSellerOrderDetail.setOnClickListener {
                onOrderDetailClick(order)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellerOrderViewHolder {
        val binding = ItemSellerOrderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SellerOrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SellerOrderViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size

    fun updateList(newList: List<Order>) {
        orders = newList
        notifyDataSetChanged()
    }
}
