package com.aurabeauty.app.data.model

import java.io.Serializable

data class User(
    val id: String,
    var name: String,
    var email: String,
    var role: String, // "comprador", "vendedor", "admin"
    var status: String = "activo", // "activo", "bloqueado"
    var phone: String = "+57 300 123 4567",
    var defaultAddress: String = "Cra. 15 # 93-21, Bogotá"
) : Serializable {

    fun isBlocked(): Boolean = status.equals("bloqueado", ignoreCase = true)

    fun getDisplayRole(): String {
        return when (role.lowercase()) {
            "comprador" -> "Compradora"
            "vendedor" -> "Vendedor"
            "admin" -> "Administrador"
            else -> role
        }
    }
}
