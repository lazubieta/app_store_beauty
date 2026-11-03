package com.aurabeauty.app.ui.admin

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
import com.aurabeauty.app.databinding.FragmentAdminUsersBinding
import com.aurabeauty.app.utils.showToast

class AdminUsersFragment : Fragment() {

    private var _binding: FragmentAdminUsersBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AdminUserAdapter
    private var roleFilter: String? = null // null = all, "comprador", "vendedor"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupFilterChips()
        setupSearch()
        loadUsers()
    }

    private fun setupRecyclerView() {
        adapter = AdminUserAdapter(
            users = emptyList(),
            onEditUserClick = { user ->
                val intent = Intent(requireContext(), UserDetailActivity::class.java).apply {
                    putExtra("user_id", user.id)
                }
                startActivity(intent)
            },
            onToggleBlockClick = { user ->
                MockDataRepository.toggleUserBlock(user.id)
                loadUsers()
                val msg = if (user.isBlocked()) "Usuario bloqueado por seguridad" else "Usuario desbloqueado"
                requireContext().showToast(msg)
            }
        )
        binding.rvAdminUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAdminUsers.adapter = adapter
    }

    private fun setupFilterChips() {
        binding.chipAdminAllUsers.setOnClickListener {
            roleFilter = null
            updateChipStyles()
            loadUsers()
        }

        binding.chipAdminBuyers.setOnClickListener {
            roleFilter = "comprador"
            updateChipStyles()
            loadUsers()
        }

        binding.chipAdminSellers.setOnClickListener {
            roleFilter = "vendedor"
            updateChipStyles()
            loadUsers()
        }
    }

    private fun updateChipStyles() {
        val selectedBg = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_selected)
        val selectedText = ContextCompat.getColor(requireContext(), R.color.on_primary)
        val unselectedBg = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_unselected)
        val unselectedText = ContextCompat.getColor(requireContext(), R.color.text_secondary)

        // Reset all
        binding.chipAdminAllUsers.background = unselectedBg
        binding.chipAdminAllUsers.setTextColor(unselectedText)
        binding.chipAdminBuyers.background = unselectedBg
        binding.chipAdminBuyers.setTextColor(unselectedText)
        binding.chipAdminSellers.background = unselectedBg
        binding.chipAdminSellers.setTextColor(unselectedText)

        when (roleFilter) {
            "comprador" -> {
                binding.chipAdminBuyers.background = selectedBg
                binding.chipAdminBuyers.setTextColor(selectedText)
            }
            "vendedor" -> {
                binding.chipAdminSellers.background = selectedBg
                binding.chipAdminSellers.setTextColor(selectedText)
            }
            else -> {
                binding.chipAdminAllUsers.background = selectedBg
                binding.chipAdminAllUsers.setTextColor(selectedText)
            }
        }
    }

    private fun setupSearch() {
        binding.etSearchAdminUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString().orEmpty()
                var list = MockDataRepository.getUsers()
                if (roleFilter != null) {
                    list = list.filter { it.role.equals(roleFilter, ignoreCase = true) }
                }
                if (query.isNotEmpty()) {
                    list = list.filter {
                        it.name.contains(query, ignoreCase = true) || it.email.contains(query, ignoreCase = true)
                    }
                }
                adapter.updateList(list)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun loadUsers() {
        var list = MockDataRepository.getUsers()
        if (roleFilter != null) {
            list = list.filter { it.role.equals(roleFilter, ignoreCase = true) }
        }
        adapter.updateList(list)
    }

    override fun onResume() {
        super.onResume()
        loadUsers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
