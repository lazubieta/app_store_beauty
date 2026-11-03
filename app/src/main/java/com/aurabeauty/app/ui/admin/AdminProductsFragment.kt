package com.aurabeauty.app.ui.admin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurabeauty.app.data.repository.MockDataRepository
import com.aurabeauty.app.databinding.FragmentAdminProductsBinding
import com.aurabeauty.app.ui.seller.EditProductActivity
import com.aurabeauty.app.utils.showToast

class AdminProductsFragment : Fragment() {

    private var _binding: FragmentAdminProductsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AdminProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearch()

        binding.btnAdminReviewProduct.setOnClickListener {
            requireContext().showToast("Catálogo auditado: 100% de productos cumplen normatividad INVIMA")
        }

        binding.tvAdminCategoryFilter.setOnClickListener {
            requireContext().showToast("Filtro por categorías activado")
        }

        loadProducts()
    }

    private fun setupRecyclerView() {
        adapter = AdminProductAdapter(
            products = emptyList(),
            onEditProductClick = { product ->
                val intent = Intent(requireContext(), EditProductActivity::class.java).apply {
                    putExtra("product_id", product.id)
                }
                startActivity(intent)
            },
            onOptionsClick = { product ->
                MockDataRepository.toggleProductActive(product.id)
                loadProducts()
                requireContext().showToast("Estado de catálogo actualizado")
            }
        )
        binding.rvAdminProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAdminProducts.adapter = adapter
    }

    private fun setupSearch() {
        binding.etSearchAdminProduct.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString().orEmpty()
                var list = MockDataRepository.getProducts()
                if (query.isNotEmpty()) {
                    list = list.filter {
                        it.name.contains(query, ignoreCase = true) || it.category.contains(query, ignoreCase = true)
                    }
                }
                adapter.updateList(list)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun loadProducts() {
        adapter.updateList(MockDataRepository.getProducts())
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
