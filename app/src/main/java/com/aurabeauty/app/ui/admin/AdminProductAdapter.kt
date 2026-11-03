package com.aurabeauty.app.ui.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aurabeauty.app.R
import com.aurabeauty.app.data.model.Product
import com.aurabeauty.app.databinding.ItemAdminProductBinding

class AdminProductAdapter(
    private var products: List<Product>,
    private val onEditProductClick: (Product) -> Unit,
    private val onOptionsClick: ((Product) -> Unit)? = null
) : RecyclerView.Adapter<AdminProductAdapter.AdminProductViewHolder>() {

    inner class AdminProductViewHolder(private val binding: ItemAdminProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            val context = binding.root.context
            binding.tvAdminProductName.text = product.name
            binding.tvAdminProductCategory.text = "Maquillaje • ${product.category}"
            binding.tvAdminProductPrice.text = product.getFormattedPrice()

            if (product.isInStock()) {
                binding.tvAdminProductStatusBadge.text = context.getString(R.string.status_active)
                binding.tvAdminProductStatusBadge.background = ContextCompat.getDrawable(context, R.drawable.bg_badge_active)
                binding.tvAdminProductStatusBadge.setTextColor(ContextCompat.getColor(context, R.color.status_active_text))
            } else {
                binding.tvAdminProductStatusBadge.text = context.getString(R.string.tab_out_of_stock)
                binding.tvAdminProductStatusBadge.background = ContextCompat.getDrawable(context, R.drawable.bg_badge_blocked)
                binding.tvAdminProductStatusBadge.setTextColor(ContextCompat.getColor(context, R.color.status_blocked_text))
            }

            binding.btnAdminEditProduct.setOnClickListener {
                onEditProductClick(product)
            }

            binding.btnAdminProductOptions.setOnClickListener {
                onOptionsClick?.invoke(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminProductViewHolder {
        val binding = ItemAdminProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AdminProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdminProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    fun updateList(newList: List<Product>) {
        products = newList
        notifyDataSetChanged()
    }
}
