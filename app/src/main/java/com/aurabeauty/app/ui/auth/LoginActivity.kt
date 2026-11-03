package com.aurabeauty.app.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aurabeauty.app.R
import com.aurabeauty.app.data.repository.MockDataRepository
import com.aurabeauty.app.databinding.ActivityLoginBinding
import com.aurabeauty.app.ui.admin.AdminMainActivity
import com.aurabeauty.app.ui.buyer.BuyerMainActivity
import com.aurabeauty.app.ui.seller.SellerMainActivity
import com.aurabeauty.app.utils.showToast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var selectedRole: String = "comprador"

    private lateinit var biometricHelper: BiometricAuthHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRoleSelector()
        setupBiometrics()
        setupListeners()
    }

    private fun setupRoleSelector() {
        // Default selection: Comprador
        updateRoleUI("comprador")

        binding.toggleGroupRole.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnRoleBuyer -> updateRoleUI("comprador")
                    R.id.btnRoleSeller -> updateRoleUI("vendedor")
                    R.id.btnRoleAdmin -> updateRoleUI("admin")
                }
            }
        }
    }

    private fun updateRoleUI(role: String) {
        selectedRole = role
        when (role) {
            "comprador" -> {
                binding.tvLoginSubtitle.text = getString(R.string.app_slogan)
                binding.etEmail.setText("camila.torres@email.com")
                binding.etPassword.setText("123456")
            }
            "vendedor" -> {
                binding.tvLoginSubtitle.text = getString(R.string.title_portal_seller)
                binding.etEmail.setText("laura.seller@aurabeauty.com")
                binding.etPassword.setText("123456")
            }
            "admin" -> {
                binding.tvLoginSubtitle.text = getString(R.string.title_portal_admin)
                binding.etEmail.setText("valentina.admin@aurabeauty.com")
                binding.etPassword.setText("admin123")
            }
        }
    }

    private fun setupBiometrics() {
        biometricHelper = BiometricAuthHelper(
            activity = this,
            onSuccess = {
                showToast(getString(R.string.biometric_success))
                proceedToMainByRole(selectedRole)
            },
            onError = { errorMsg ->
                showToast(errorMsg)
            }
        )
    }

    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text?.toString()?.trim().orEmpty()
            val password = binding.etPassword.text?.toString()?.trim().orEmpty()

            if (email.isEmpty()) {
                binding.tilEmail.error = getString(R.string.msg_field_required)
                return@setOnClickListener
            }
            binding.tilEmail.error = null

            if (password.isEmpty()) {
                binding.tilPassword.error = getString(R.string.msg_field_required)
                return@setOnClickListener
            }
            binding.tilPassword.error = null

            proceedToMainByRole(selectedRole)
        }

        binding.btnBiometricLogin.setOnClickListener {
            biometricHelper.authenticate()
        }

        binding.btnCreateAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, RecoverPasswordActivity::class.java))
        }
    }

    private fun proceedToMainByRole(role: String) {
        // Set current user in repository
        val users = MockDataRepository.getUsers()
        MockDataRepository.currentUser = users.find { it.role.equals(role, ignoreCase = true) } ?: users.first()

        val intent = when (role) {
            "vendedor" -> Intent(this, SellerMainActivity::class.java)
            "admin" -> Intent(this, AdminMainActivity::class.java)
            else -> Intent(this, BuyerMainActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
