package com.aurabeauty.app.ui.buyer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurabeauty.app.R
import com.aurabeauty.app.data.model.Product
import com.aurabeauty.app.data.repository.MockDataRepository
import com.aurabeauty.app.databinding.ActivityProductDetailBinding
import com.aurabeauty.app.utils.showToast

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getStringExtra("product_id") ?: "p1"
        product = MockDataRepository.getProductById(productId) ?: MockDataRepository.getProducts().firstOrNull()

        setupUI()
        setupToneSelector()
        setupListeners()
    }

    private fun setupUI() {
        product?.let { p ->
            binding.tvDetailTitle.text = p.name
            binding.tvDetailDescription.text = p.description
            binding.tvDetailPrice.text = p.getFormattedPrice()
        }
    }

    private fun setupToneSelector() {
        val tones = MockDataRepository.getSampleTones()
        val adapter = ToneAdapter(tones) { selectedTone ->
            showToast("Tono seleccionado: ${selectedTone.name}")
        }
        binding.rvTones.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTones.adapter = adapter
    }

    private fun setupListeners() {
        binding.btnBackDetail.setOnClickListener { finish() }

        binding.btnFavoriteDetail.setOnClickListener {
            product?.let { p ->
                p.isFavorite = !p.isFavorite
                val msg = if (p.isFavorite) "Añadido a favoritos 💖" else "Eliminado de favoritos"
                showToast(msg)
            }
        }

        binding.btnCartDetail.setOnClickListener {
            finish()
        }

        binding.btnAddToCart.setOnClickListener {
            product?.let { p ->
                MockDataRepository.addToCart(p)
                showToast(getString(R.string.msg_product_added_to_cart))
            }
        }
    }
}
