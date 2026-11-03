package com.aurabeauty.app.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aurabeauty.app.R
import com.aurabeauty.app.databinding.ActivityRecoverPasswordBinding
import com.aurabeauty.app.utils.showToast

class RecoverPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecoverPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoverPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackRecover.setOnClickListener { finish() }

        binding.btnSendRecovery.setOnClickListener {
            val email = binding.etRecoverEmail.text?.toString()?.trim().orEmpty()
            if (email.isEmpty()) {
                binding.tilRecoverEmail.error = getString(R.string.msg_field_required)
                return@setOnClickListener
            }
            binding.tilRecoverEmail.error = null

            showToast(getString(R.string.msg_recovery_sent))
            finish()
        }
    }
}
