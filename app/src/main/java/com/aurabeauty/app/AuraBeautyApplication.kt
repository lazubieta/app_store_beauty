package com.aurabeauty.app

import android.app.Application
import com.aurabeauty.app.data.repository.MockDataRepository

class AuraBeautyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize mock repository with seed data
        MockDataRepository.initialize(this)
    }
}
