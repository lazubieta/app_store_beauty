package com.aurabeauty.app.ui.seller

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.aurabeauty.app.R
import com.aurabeauty.app.data.repository.MockDataRepository
import com.aurabeauty.app.databinding.ActivityAddProductBinding
import com.aurabeauty.app.utils.showToast

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinners()
        setupListeners()
    }

    private fun setupSpinners() {
        val brands = listOf("Aura Beauty", "Glow Care", "Lash Velvet", "Skin Pure")
        val brandAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, brands)
        binding.spinnerAddProductBrand.adapter = brandAdapter

        val categories = listOf("Labiales", "Rostro", "Ojos", "Piel")
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        binding.spinnerAddProductCategory.adapter = categoryAdapter
    }

    private fun setupListeners() {
        binding.btnBackAddProduct.setOnClickListener { finish() }

        binding.boxAddProductPhoto.setOnClickListener {
            showToast("Acceso a cámara para captura de producto habilitado (CameraX)")
        }

        binding.btnSaveNewProduct.setOnClickListener {
            val name = binding.etAddProductName.text?.toString()?.trim().orEmpty()
            val tone = binding.etAddProductTone.text?.toString()?.trim().orEmpty()
            val priceStr = binding.etAddProductPrice.text?.toString()?.trim().orEmpty()
            val stockStr = binding.etAddProductStock.text?.toString()?.trim().orEmpty()

            if (name.isEmpty()) {
                binding.tilAddProductName.error = getString(R.string.msg_field_required)
                return@setOnClickListener
            }
            binding.tilAddProductName.error = null

            val price = priceStr.toDoubleOrNull() ?: 39900.0
            val stock = stockStr.toIntOrNull() ?: 10
            val brand = binding.spinnerAddProductBrand.selectedItem.toString()
            val category = binding.spinnerAddProductCategory.selectedItem.toString()

            MockDataRepository.addProduct(
                name = name,
                brand = brand,
                category = category,
                tone = if (tone.isEmpty()) "Tono Único" else tone,
                price = price,
                stock = stock
            )

            showToast(getString(R.string.msg_product_saved))
            finish()
        }
    }
}
