package com.aurabeauty.app.data.model

import java.io.Serializable

data class Product(
    val id: String,
    var name: String,
    var brand: String,
    var category: String, // "Labiales", "Rostro", "Ojos", "Piel"
    var tone: String,
    var price: Double,
    var stock: Int,
    var isActive: Boolean = true,
    var description: String = "Cosmético de alta calidad con pigmentación duradera y textura aterciopelada.",
    var rating: Double = 4.8,
    var reviewsCount: Int = 120,
    var isFavorite: Boolean = false
) : Serializable {

    fun isInStock(): Boolean = stock > 0 && isActive

    fun getFormattedPrice(): String {
        return "$ %,.0f".format(price).replace(',', '.')
    }
}
