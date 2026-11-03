package com.aurabeauty.app.ui.buyer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurabeauty.app.data.repository.MockDataRepository
import com.aurabeauty.app.databinding.FragmentCartBinding
import com.aurabeauty.app.utils.showToast
import com.aurabeauty.app.utils.toCurrencyCOP

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupListeners()
        updateCartSummary()
    }

    private fun setupRecyclerView() {
        adapter = CartAdapter(
            items = MockDataRepository.getCartItems(),
            onQuantityChange = { item, delta ->
                MockDataRepository.updateCartQuantity(item.product.id, delta)
                refreshCart()
            },
            onRemoveItem = { item ->
                MockDataRepository.removeFromCart(item.product.id)
                refreshCart()
                requireContext().showToast("Producto eliminado del carrito")
            }
        )
        binding.rvCartItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCartItems.adapter = adapter
    }

    private fun setupListeners() {
        binding.btnClearCart.setOnClickListener {
            MockDataRepository.clearCart()
            refreshCart()
            requireContext().showToast("Carrito vaciado")
        }

        binding.btnProceedToCheckout.setOnClickListener {
            if (MockDataRepository.getCartItems().isEmpty()) {
                requireContext().showToast("Tu carrito está vacío. Agrega cosméticos para continuar.")
                return@setOnClickListener
            }
            startActivity(Intent(requireContext(), SecurePaymentActivity::class.java))
        }
    }

    private fun refreshCart() {
        adapter.updateList(MockDataRepository.getCartItems())
        updateCartSummary()
    }

    private fun updateCartSummary() {
        val subtotal = MockDataRepository.getCartSubtotal()
        val shipping = MockDataRepository.getCartShipping()
        val total = MockDataRepository.getCartTotal()

        binding.tvCartSubtotal.text = subtotal.toCurrencyCOP()
        binding.tvCartShipping.text = shipping.toCurrencyCOP()
        binding.tvCartTotal.text = total.toCurrencyCOP()
    }

    override fun onResume() {
        super.onResume()
        refreshCart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
