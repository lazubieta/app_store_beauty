package com.aurabeauty.app.ui.seller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurabeauty.app.data.repository.MockDataRepository
import com.aurabeauty.app.databinding.FragmentSellerOrdersBinding
import com.aurabeauty.app.utils.showToast

class SellerOrdersFragment : Fragment() {

    private var _binding: FragmentSellerOrdersBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SellerOrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSellerOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SellerOrderAdapter(
            orders = MockDataRepository.getOrders(),
            onOrderDetailClick = { order ->
                requireContext().showToast("Detalle de ${order.orderNumber}: Total ${order.total} COP")
            }
        )
        binding.rvSellerOrders.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSellerOrders.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
