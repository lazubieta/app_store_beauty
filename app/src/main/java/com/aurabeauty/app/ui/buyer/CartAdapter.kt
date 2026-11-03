package com.aurabeauty.app.ui.buyer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aurabeauty.app.data.model.CartItem
import com.aurabeauty.app.databinding.ItemCartProductBinding

class CartAdapter(
    private var items: List<CartItem>,
    private val onQuantityChange: (CartItem, Int) -> Unit,
    private val onRemoveItem: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: ItemCartProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartItem) {
            binding.tvCartItemTitle.text = item.product.name
            binding.tvCartItemTone.text = "Tono: ${item.product.tone}"
            binding.tvCartItemPrice.text = item.product.getFormattedPrice()
            binding.tvCartQtyValue.text = item.quantity.toString()

            binding.btnCartQtyPlus.setOnClickListener {
                onQuantityChange(item, 1)
            }

            binding.btnCartQtyMinus.setOnClickListener {
                onQuantityChange(item, -1)
            }

            binding.btnRemoveCartItem.setOnClickListener {
                onRemoveItem(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newList: List<CartItem>) {
        items = newList
        notifyDataSetChanged()
    }
}
