package com.aurabeauty.app.data.model

import java.io.Serializable

data class AuditLog(
    val id: String,
    val timestamp: String,
    val actionTitle: String,
    val actorName: String,
    val actorRole: String,
    val actionType: String // "login", "blocked", "product_update", "payment", "new_user"
) : Serializable
