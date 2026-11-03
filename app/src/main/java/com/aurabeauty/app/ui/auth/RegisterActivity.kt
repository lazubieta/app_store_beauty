package com.aurabeauty.app.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aurabeauty.app.R
import com.aurabeauty.app.databinding.ActivityRegisterBinding
import com.aurabeauty.app.utils.showToast

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var selectedRole = "comprador"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackRegister.setOnClickListener { finish() }

        binding.toggleGroupRegisterRole.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                selectedRole = if (checkedId == R.id.btnRegisterSeller) "vendedor" else "comprador"
            }
        }

        binding.btnSubmitRegister.setOnClickListener {
            val name = binding.etFullName.text?.toString()?.trim().orEmpty()
            val email = binding.etRegisterEmail.text?.toString()?.trim().orEmpty()
            val phone = binding.etRegisterPhone.text?.toString()?.trim().orEmpty()
            val password = binding.etRegisterPassword.text?.toString()?.trim().orEmpty()

            if (name.isEmpty()) {
                binding.tilFullName.error = getString(R.string.msg_field_required)
                return@setOnClickListener
            }
            binding.tilFullName.error = null

            if (email.isEmpty()) {
                binding.tilRegisterEmail.error = getString(R.string.msg_field_required)
                return@setOnClickListener
            }
            binding.tilRegisterEmail.error = null

            if (password.isEmpty()) {
                binding.tilRegisterPassword.error = getString(R.string.msg_field_required)
                return@setOnClickListener
            }
            binding.tilRegisterPassword.error = null

            if (!binding.cbTermsConsent.isChecked) {
                showToast("Debes aceptar el tratamiento de datos personales conforme a la Ley 1581 de 2012.")
                return@setOnClickListener
            }

            showToast("Cuenta creada exitosamente para $name. Por favor inicia sesión.")
            finish()
        }
    }
}
