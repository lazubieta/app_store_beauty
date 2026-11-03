package com.aurabeauty.app.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurabeauty.app.data.repository.MockDataRepository
import com.aurabeauty.app.databinding.FragmentAdminAuditPaymentsBinding
import com.aurabeauty.app.utils.showToast

class AdminReportsAuditFragment : Fragment() {

    private var _binding: FragmentAdminAuditPaymentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AuditLogAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminAuditPaymentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AuditLogAdapter(MockDataRepository.getAuditLogs())
        binding.rvAdminAuditLogs.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAdminAuditLogs.adapter = adapter

        binding.tvAuditViewAllLogs.setOnClickListener {
            requireContext().showToast("Mostrando todos los registros de auditoría (Ley 527 de 1999)")
        }

        binding.btnAdminViewLogs.setOnClickListener {
            requireContext().showToast("Exportando logs del sistema con firma digital para auditoría externa")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
