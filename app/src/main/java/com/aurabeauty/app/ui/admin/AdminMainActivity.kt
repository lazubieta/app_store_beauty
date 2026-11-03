package com.aurabeauty.app.ui.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aurabeauty.app.R
import com.aurabeauty.app.databinding.ActivityAdminMainBinding

class AdminMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()

        if (savedInstanceState == null) {
            loadFragment(AdminDashboardFragment())
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavAdmin.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_admin_home -> {
                    loadFragment(AdminDashboardFragment())
                    true
                }
                R.id.nav_admin_users -> {
                    loadFragment(AdminUsersFragment())
                    true
                }
                R.id.nav_admin_products -> {
                    loadFragment(AdminProductsFragment())
                    true
                }
                R.id.nav_admin_payments -> {
                    loadFragment(AdminReportsAuditFragment())
                    true
                }
                R.id.nav_admin_more -> {
                    loadFragment(AdminReportsAuditFragment())
                    true
                }
                else -> false
            }
        }
    }

    fun navigateToTab(itemId: Int) {
        binding.bottomNavAdmin.selectedItemId = itemId
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.adminFragmentContainer, fragment)
            .commit()
    }
}
