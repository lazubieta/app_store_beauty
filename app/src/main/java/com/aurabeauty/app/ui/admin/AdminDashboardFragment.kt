package com.aurabeauty.app.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aurabeauty.app.R
import com.aurabeauty.app.databinding.FragmentAdminDashboardBinding
import com.aurabeauty.app.utils.showToast

class AdminDashboardFragment : Fragment() {

    private var _binding: FragmentAdminDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnQuickAdminUsers.setOnClickListener {
            (activity as? AdminMainActivity)?.navigateToTab(R.id.nav_admin_users)
        }

        binding.btnQuickAdminProducts.setOnClickListener {
            (activity as? AdminMainActivity)?.navigateToTab(R.id.nav_admin_products)
        }

        binding.btnQuickAdminAudit.setOnClickListener {
            (activity as? AdminMainActivity)?.navigateToTab(R.id.nav_admin_payments)
        }

        binding.btnQuickAdminReports.setOnClickListener {
            requireContext().showToast("Generando reporte financiero consolidado en PDF")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
