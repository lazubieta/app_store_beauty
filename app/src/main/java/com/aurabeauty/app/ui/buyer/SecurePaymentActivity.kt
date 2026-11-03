package com.aurabeauty.app.ui.buyer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aurabeauty.app.R
import com.aurabeauty.app.data.repository.MockDataRepository
import com.aurabeauty.app.databinding.ActivitySecurePaymentBinding
import com.aurabeauty.app.utils.showToast

class SecurePaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecurePaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecurePaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackPayment.setOnClickListener { finish() }

        binding.btnEditAddress.setOnClickListener {
            showToast("Edición de dirección de entrega habilitada")
        }

        binding.btnEditPaymentMethod.setOnClickListener {
            showToast("Edición de pasarela / tarjeta habilitada")
        }

        binding.btnConfirmPayment.setOnClickListener {
            val user = MockDataRepository.currentUser
            val clientName = user?.name ?: "Camila Torres"
            val newOrder = MockDataRepository.createOrderFromCart(clientName)

            showToast(getString(R.string.msg_payment_success))

            val intent = Intent(this, OrderTrackingActivity::class.java).apply {
                putExtra("order_id", newOrder.id)
            }
            startActivity(intent)
            finish()
        }
    }
}
