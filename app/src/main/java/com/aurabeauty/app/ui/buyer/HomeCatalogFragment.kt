package com.aurabeauty.app.ui.buyer

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.aurabeauty.app.R
import com.aurabeauty.app.data.model.Product
import com.aurabeauty.app.data.repository.MockDataRepository
import com.aurabeauty.app.databinding.FragmentBuyerHomeBinding
import com.aurabeauty.app.utils.showToast

class HomeCatalogFragment : Fragment() {

    private var _binding: FragmentBuyerHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ProductAdapter
    private var onlyFavorites: Boolean = false
    private var selectedCategory: String? = null

    companion object {
        fun newInstance(onlyFavorites: Boolean = false): HomeCatalogFragment {
            val fragment = HomeCatalogFragment()
            fragment.onlyFavorites = onlyFavorites
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyerHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupCategoryChips()
        setupSearch()
        loadProducts()
    }

    private fun setupRecyclerView() {
        adapter = ProductAdapter(
            products = emptyList(),
            onProductClick = { product ->
                val intent = Intent(requireContext(), ProductDetailActivity::class.java).apply {
                    putExtra("product_id", product.id)
                }
                startActivity(intent)
            },
            onFavoriteClick = { product ->
                val msg = if (product.isFavorite) "Añadido a favoritos 💖" else "Eliminado de favoritos"
                requireContext().showToast(msg)
            }
        )
        binding.rvFeaturedProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvFeaturedProducts.adapter = adapter
    }

    private fun setupCategoryChips() {
        binding.chipCategoryLips.setOnClickListener {
            toggleCategory("Labiales", binding.chipCategoryLips)
        }
        binding.chipCategoryEyes.setOnClickListener {
            toggleCategory("Ojos", binding.chipCategoryEyes)
        }
        binding.chipCategorySkin.setOnClickListener {
            toggleCategory("Piel", binding.chipCategorySkin)
        }
    }

    private fun toggleCategory(category: String, chipView: View) {
        if (selectedCategory == category) {
            selectedCategory = null
            resetChipStyles()
        } else {
            selectedCategory = category
            resetChipStyles()
            chipView.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_selected)
            (chipView as? android.widget.TextView)?.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.on_primary)
            )
        }
        loadProducts()
    }

    private fun resetChipStyles() {
        val unselectedBg = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_unselected)
        val unselectedColor = ContextCompat.getColor(requireContext(), R.color.text_secondary)

        binding.chipCategoryLips.background = unselectedBg
        binding.chipCategoryLips.setTextColor(unselectedColor)

        binding.chipCategoryEyes.background = unselectedBg
        binding.chipCategoryEyes.setTextColor(unselectedColor)

        binding.chipCategorySkin.background = unselectedBg
        binding.chipCategorySkin.setTextColor(unselectedColor)
    }

    private fun setupSearch() {
        binding.etSearchMakeup.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterProducts(s?.toString().orEmpty())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun loadProducts() {
        var list = MockDataRepository.getProducts()
        if (onlyFavorites) {
            list = list.filter { it.isFavorite }
        }
        if (selectedCategory != null) {
            list = list.filter { it.category.equals(selectedCategory, ignoreCase = true) }
        }
        adapter.updateList(list)
    }

    private fun filterProducts(query: String) {
        var list = MockDataRepository.getProducts()
        if (onlyFavorites) {
            list = list.filter { it.isFavorite }
        }
        if (selectedCategory != null) {
            list = list.filter { it.category.equals(selectedCategory, ignoreCase = true) }
        }
        if (query.isNotEmpty()) {
            list = list.filter {
                it.name.contains(query, ignoreCase = true) ||
                it.tone.contains(query, ignoreCase = true) ||
                it.brand.contains(query, ignoreCase = true)
            }
        }
        adapter.updateList(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
