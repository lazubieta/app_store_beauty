package com.aurabeauty.app.ui.admin

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.aurabeauty.app.R
import com.aurabeauty.app.data.model.User
import com.aurabeauty.app.data.repository.MockDataRepository
import com.aurabeauty.app.databinding.ActivityUserDetailBinding
import com.aurabeauty.app.utils.showToast

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getStringExtra("user_id") ?: "u2"
        user = MockDataRepository.getUserById(userId)

        setupSpinners()
        populateUI()
        setupListeners()
    }

    private fun setupSpinners() {
        val roles = listOf("Compradora", "Vendedor", "Administrador")
        val roleAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roles)
        binding.spinnerDetailUserRole.adapter = roleAdapter

        val statuses = listOf("Activo", "Bloqueado")
        val statusAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, statuses)
        binding.spinnerDetailUserStatus.adapter = statusAdapter
    }

    private fun populateUI() {
        user?.let { u ->
            binding.tvDetailUserFullName.text = u.name
            binding.tvDetailUserRoleSubtitle.text = u.getDisplayRole()
            binding.etDetailUserName.setText(u.name)
            binding.etDetailUserEmail.setText(u.email)

            updateStatusBadge(u.isBlocked())
        }
    }

    private fun updateStatusBadge(isBlocked: Boolean) {
        if (isBlocked) {
            binding.tvDetailUserStatusBadge.text = getString(R.string.status_blocked)
            binding.tvDetailUserStatusBadge.background = ContextCompat.getDrawable(this, R.drawable.bg_badge_blocked)
            binding.tvDetailUserStatusBadge.setTextColor(ContextCompat.getColor(this, R.color.status_blocked_text))
            binding.btnToggleBlockUser.text = getString(R.string.btn_unblock_user)
            binding.spinnerDetailUserStatus.setSelection(1)
        } else {
            binding.tvDetailUserStatusBadge.text = getString(R.string.status_active)
            binding.tvDetailUserStatusBadge.background = ContextCompat.getDrawable(this, R.drawable.bg_badge_active)
            binding.tvDetailUserStatusBadge.setTextColor(ContextCompat.getColor(this, R.color.status_active_text))
            binding.btnToggleBlockUser.text = getString(R.string.btn_block_user)
            binding.spinnerDetailUserStatus.setSelection(0)
        }
    }

    private fun setupListeners() {
        binding.btnBackUserDetail.setOnClickListener { finish() }

        binding.btnToggleBlockUser.setOnClickListener {
            val u = user ?: return@setOnClickListener
            MockDataRepository.toggleUserBlock(u.id)
            updateStatusBadge(u.isBlocked())
            val msg = if (u.isBlocked()) "Usuario bloqueado preventivamente" else "Usuario activado"
            showToast(msg)
        }

        binding.btnSaveUserDetailChanges.setOnClickListener {
            val u = user ?: return@setOnClickListener
            val name = binding.etDetailUserName.text?.toString()?.trim().orEmpty()
            val email = binding.etDetailUserEmail.text?.toString()?.trim().orEmpty()
            val roleRaw = binding.spinnerDetailUserRole.selectedItem.toString()
            val role = when (roleRaw) {
                "Compradora" -> "comprador"
                "Vendedor" -> "vendedor"
                else -> "admin"
            }
            val status = if (binding.spinnerDetailUserStatus.selectedItemPosition == 1) "bloqueado" else "activo"

            MockDataRepository.updateUser(u.id, name, email, role, status)
            showToast(getString(R.string.msg_user_status_updated))
            finish()
        }
    }
}
