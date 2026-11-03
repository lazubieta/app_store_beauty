package com.aurabeauty.app.data.model

import java.io.Serializable

data class Order(
    val id: String,
    val orderNumber: String,
    val date: String,
    var status: String, // "pendiente", "en_preparacion", "en_camino", "entregado"
    val clientName: String,
    val itemsCount: Int,
    val total: Double,
    val deliveryAddress: String = "Cra. 15 # 93-21, Bogotá"
) : Serializable {

    fun getStatusLabel(): String {
        return when (status.lowercase()) {
            "pendiente" -> "Pendiente"
            "en_preparacion" -> "En preparación"
            "en_camino" -> "En camino"
            "entregado" -> "Entregado"
            else -> status
        }
    }
}
