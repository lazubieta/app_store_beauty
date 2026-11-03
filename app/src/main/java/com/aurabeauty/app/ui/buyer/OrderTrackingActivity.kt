package com.aurabeauty.app.ui.buyer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aurabeauty.app.databinding.ActivityOrderTrackingBinding
import com.aurabeauty.app.utils.showToast

class OrderTrackingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderTrackingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderTrackingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackTracking.setOnClickListener { finish() }

        binding.btnOrderTrackingViewDetail.setOnClickListener {
            showToast("Comprobante legal y trazabilidad de orden conforme a Ley 527 de 1999")
        }
    }
}
