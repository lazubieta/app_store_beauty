package com.aurabeauty.app.ui.seller

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.aurabeauty.app.R
import com.aurabeauty.app.data.model.Product
import com.aurabeauty.app.data.repository.MockDataRepository
import com.aurabeauty.app.databinding.ActivityEditProductBinding
import com.aurabeauty.app.utils.showToast

class EditProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProductBinding
    private var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getStringExtra("product_id") ?: "p1"
        product = MockDataRepository.getProductById(productId)

        setupSpinners()
        populateFields()
        setupListeners()
    }

    private fun setupSpinners() {
        val brands = listOf("Aura Beauty", "Glow Care", "Lash Velvet", "Skin Pure")
        val brandAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, brands)
        binding.spinnerEditProductBrand.adapter = brandAdapter

        val categories = listOf("Labiales", "Rostro", "Ojos", "Piel")
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        binding.spinnerEditProductCategory.adapter = categoryAdapter
    }

    private fun populateFields() {
        product?.let { p ->
            binding.etEditProductName.setText(p.name)
            binding.etEditProductTone.setText(p.tone)
            binding.etEditProductPrice.setText(p.price.toInt().toString())
            binding.etEditProductStock.setText(p.stock.toString())

            binding.btnDeactivateProduct.text = if (p.isActive) getString(R.string.btn_deactivate_product) else "Activar producto"
        }
    }

    private fun setupListeners() {
        binding.btnBackEditProduct.setOnClickListener { finish() }

        binding.btnChangeProductPhoto.setOnClickListener {
            showToast("Acceso a galería / cámara para actualizar foto")
        }

        binding.btnUpdateProduct.setOnClickListener {
            val p = product ?: return@setOnClickListener
            val name = binding.etEditProductName.text?.toString()?.trim().orEmpty()
            val tone = binding.etEditProductTone.text?.toString()?.trim().orEmpty()
            val price = binding.etEditProductPrice.text?.toString()?.toDoubleOrNull() ?: p.price
            val stock = binding.etEditProductStock.text?.toString()?.toIntOrNull() ?: p.stock
            val brand = binding.spinnerEditProductBrand.selectedItem.toString()
            val category = binding.spinnerEditProductCategory.selectedItem.toString()

            MockDataRepository.updateProduct(p.id, name, brand, category, tone, price, stock)
            showToast(getString(R.string.msg_product_updated))
            finish()
        }

        binding.btnDeactivateProduct.setOnClickListener {
            val p = product ?: return@setOnClickListener
            val newActive = MockDataRepository.toggleProductActive(p.id)
            val msg = if (newActive) "Producto activado" else "Producto desactivado"
            showToast(msg)
            finish()
        }
    }
}
