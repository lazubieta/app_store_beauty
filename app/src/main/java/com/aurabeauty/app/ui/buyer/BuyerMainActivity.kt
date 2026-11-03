package com.aurabeauty.app.ui.buyer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aurabeauty.app.R
import com.aurabeauty.app.databinding.ActivityBuyerMainBinding

class BuyerMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBuyerMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyerMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()

        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(HomeCatalogFragment())
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavBuyer.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_buyer_home -> {
                    loadFragment(HomeCatalogFragment())
                    true
                }
                R.id.nav_buyer_favorites -> {
                    loadFragment(HomeCatalogFragment.newInstance(onlyFavorites = true))
                    true
                }
                R.id.nav_buyer_cart -> {
                    loadFragment(CartFragment())
                    true
                }
                R.id.nav_buyer_profile -> {
                    loadFragment(BuyerProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    fun navigateToTab(itemId: Int) {
        binding.bottomNavBuyer.selectedItemId = itemId
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.buyerFragmentContainer, fragment)
            .commit()
    }
}
