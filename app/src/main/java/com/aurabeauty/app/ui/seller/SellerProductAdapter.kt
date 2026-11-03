package com.aurabeauty.app.ui.seller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aurabeauty.app.data.model.Product
import com.aurabeauty.app.databinding.ItemSellerProductBinding

class SellerProductAdapter(
    private var products: List<Product>,
    private val onEditClick: (Product) -> Unit,
    private val onOptionsClick: ((Product) -> Unit)? = null
) : RecyclerView.Adapter<SellerProductAdapter.SellerProductViewHolder>() {

    inner class SellerProductViewHolder(private val binding: ItemSellerProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.tvSellerProductName.text = product.name
            binding.tvSellerProductTone.text = "Tono ${product.tone}"
            binding.tvSellerProductPrice.text = product.getFormattedPrice()
            binding.tvSellerProductStock.text = "Stock: ${product.stock}"

            binding.btnEditSellerProduct.setOnClickListener {
                onEditClick(product)
            }

            binding.btnOptionsSellerProduct.setOnClickListener {
                onOptionsClick?.invoke(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellerProductViewHolder {
        val binding = ItemSellerProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SellerProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SellerProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    fun updateList(newList: List<Product>) {
        products = newList
        notifyDataSetChanged()
    }
}
