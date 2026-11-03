package com.aurabeauty.app.ui.seller

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aurabeauty.app.R
import com.aurabeauty.app.databinding.ActivitySellerMainBinding

class SellerMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySellerMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellerMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()

        if (savedInstanceState == null) {
            loadFragment(SellerDashboardFragment())
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavSeller.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_seller_panel -> {
                    loadFragment(SellerDashboardFragment())
                    true
                }
                R.id.nav_seller_products -> {
                    loadFragment(SellerProductsFragment())
                    true
                }
                R.id.nav_seller_orders -> {
                    loadFragment(SellerOrdersFragment())
                    true
                }
                R.id.nav_seller_profile -> {
                    loadFragment(SellerProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    fun navigateToTab(itemId: Int) {
        binding.bottomNavSeller.selectedItemId = itemId
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.sellerFragmentContainer, fragment)
            .commit()
    }
}
