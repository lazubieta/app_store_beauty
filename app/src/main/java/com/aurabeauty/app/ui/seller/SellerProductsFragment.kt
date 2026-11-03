package com.aurabeauty.app.ui.seller

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurabeauty.app.R
import com.aurabeauty.app.data.repository.MockDataRepository
import com.aurabeauty.app.databinding.FragmentSellerProductsBinding
import com.aurabeauty.app.utils.showToast

class SellerProductsFragment : Fragment() {

    private var _binding: FragmentSellerProductsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SellerProductAdapter
    private var filterActiveOnly = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSellerProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupListeners()
        setupSearch()
        loadProducts()
    }

    private fun setupRecyclerView() {
        adapter = SellerProductAdapter(
            products = emptyList(),
            onEditClick = { product ->
                val intent = Intent(requireContext(), EditProductActivity::class.java).apply {
                    putExtra("product_id", product.id)
                }
                startActivity(intent)
            },
            onOptionsClick = { product ->
                val newStatus = MockDataRepository.toggleProductActive(product.id)
                loadProducts()
                val statusMsg = if (newStatus) "Producto activado" else "Producto desactivado"
                requireContext().showToast(statusMsg)
            }
        )
        binding.rvSellerProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSellerProducts.adapter = adapter
    }

    private fun setupListeners() {
        binding.btnSellerAddProductHeader.setOnClickListener {
            startActivity(Intent(requireContext(), AddProductActivity::class.java))
        }

        binding.chipSellerActive.setOnClickListener {
            filterActiveOnly = true
            updateFilterChips()
            loadProducts()
        }

        binding.chipSellerOutOfStock.setOnClickListener {
            filterActiveOnly = false
            updateFilterChips()
            loadProducts()
        }
    }

    private fun updateFilterChips() {
        if (filterActiveOnly) {
            binding.chipSellerActive.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_badge_active)
            binding.chipSellerActive.setTextColor(ContextCompat.getColor(requireContext(), R.color.status_active_text))

            binding.chipSellerOutOfStock.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_unselected)
            binding.chipSellerOutOfStock.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_secondary))
        } else {
            binding.chipSellerActive.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_unselected)
            binding.chipSellerActive.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_secondary))

            binding.chipSellerOutOfStock.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_badge_blocked)
            binding.chipSellerOutOfStock.setTextColor(ContextCompat.getColor(requireContext(), R.color.status_blocked_text))
        }
    }

    private fun setupSearch() {
        binding.etSearchSellerProduct.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString().orEmpty()
                var list = MockDataRepository.getProducts()
                list = if (filterActiveOnly) list.filter { it.isInStock() } else list.filter { !it.isInStock() }
                if (query.isNotEmpty()) {
                    list = list.filter {
                        it.name.contains(query, ignoreCase = true) || it.tone.contains(query, ignoreCase = true)
                    }
                }
                adapter.updateList(list)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun loadProducts() {
        val list = MockDataRepository.getProducts()
        val filtered = if (filterActiveOnly) list.filter { it.isInStock() } else list.filter { !it.isInStock() }
        adapter.updateList(filtered)
    }

    override fun onResume() {
        super.onResume()
        loadProducts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
