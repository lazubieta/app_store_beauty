package com.aurabeauty.app.ui.buyer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aurabeauty.app.R
import com.aurabeauty.app.data.model.Product
import com.aurabeauty.app.databinding.ItemBuyerProductBinding

class ProductAdapter(
    private var products: List<Product>,
    private val onProductClick: (Product) -> Unit,
    private val onFavoriteClick: ((Product) -> Unit)? = null
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemBuyerProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.tvItemProductName.text = product.name
            binding.tvItemProductBrand.text = "Tono ${product.tone}"
            binding.tvItemProductPrice.text = product.getFormattedPrice()

            // Favorite icon status
            val favRes = if (product.isFavorite) R.drawable.ic_favorites else R.drawable.ic_favorites
            binding.btnItemFavorite.setImageResource(favRes)

            binding.btnItemFavorite.setOnClickListener {
                product.isFavorite = !product.isFavorite
                notifyItemChanged(adapterPosition)
                onFavoriteClick?.invoke(product)
            }

            binding.root.setOnClickListener {
                onProductClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemBuyerProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    fun updateList(newList: List<Product>) {
        products = newList
        notifyDataSetChanged()
    }
}
