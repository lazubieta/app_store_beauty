package com.aurabeauty.app.data.model

import java.io.Serializable

data class CartItem(
    val product: Product,
    var quantity: Int = 1
) : Serializable {

    val subtotal: Double
        get() = product.price * quantity

    fun getFormattedSubtotal(): String {
        return "$ %,.0f".format(subtotal).replace(',', '.')
    }
}
