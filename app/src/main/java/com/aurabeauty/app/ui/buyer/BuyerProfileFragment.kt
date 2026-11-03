package com.aurabeauty.app.ui.buyer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aurabeauty.app.data.repository.MockDataRepository
import com.aurabeauty.app.databinding.FragmentBuyerProfileBinding
import com.aurabeauty.app.ui.auth.LoginActivity
import com.aurabeauty.app.utils.showToast

class BuyerProfileFragment : Fragment() {

    private var _binding: FragmentBuyerProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyerProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = MockDataRepository.currentUser
        user?.let {
            binding.tvBuyerProfileName.text = it.name
            binding.tvBuyerProfileEmail.text = it.email
        }

        binding.btnBuyerRetract.setOnClickListener {
            requireContext().showToast("Solicitud de retracto radicada (Ley 1480 de 2011). Plazo legal de 5 días hábiles.")
        }

        binding.btnBuyerLogout.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
