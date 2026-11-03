package com.aurabeauty.app.data.repository

import android.content.Context
import com.aurabeauty.app.data.model.AuditLog
import com.aurabeauty.app.data.model.CartItem
import com.aurabeauty.app.data.model.Order
import com.aurabeauty.app.data.model.Product
import com.aurabeauty.app.data.model.ToneOption
import com.aurabeauty.app.data.model.User

object MockDataRepository {

    private val usersList = mutableListOf<User>()
    private val productsList = mutableListOf<Product>()
    private val cartItemsList = mutableListOf<CartItem>()
    private val ordersList = mutableListOf<Order>()
    private val auditLogsList = mutableListOf<AuditLog>()

    var currentUser: User? = null

    fun initialize(context: Context) {
        if (usersList.isNotEmpty()) return

        // 1. Initial Users
        usersList.addAll(
            listOf(
                User("u1", "Camila Torres", "camila.torres@email.com", "comprador", "activo"),
                User("u2", "Laura Gómez", "laura.gomez@email.com", "comprador", "bloqueado"),
                User("u3", "Carlos Méndez", "carlos.mendez@email.com", "vendedor", "activo"),
                User("u4", "Laura Herrera", "laura.seller@aurabeauty.com", "vendedor", "activo"),
                User("u5", "Valentina Admin", "valentina.admin@aurabeauty.com", "admin", "activo"),
                User("u6", "Diego Ramírez", "diego.ramirez@email.com", "vendedor", "activo"),
                User("u7", "Sofía Herrera", "sofia.herrera@email.com", "comprador", "activo"),
                User("u8", "Mateo Castillo", "mateo.castillo@email.com", "vendedor", "bloqueado")
            )
        )

        // 2. Initial Products
        productsList.addAll(
            listOf(
                Product(
                    id = "p1",
                    name = "Labial Velvet",
                    brand = "Aura Beauty",
                    category = "Labiales",
                    tone = "Rosa Nude",
                    price = 39900.0,
                    stock = 24,
                    isActive = true,
                    description = "Color intenso y acabado aterciopelado que cuida y nutre tus labios con ácido hialurónico.",
                    rating = 4.8,
                    reviewsCount = 120,
                    isFavorite = true
                ),
                Product(
                    id = "p2",
                    name = "Rubor Glow",
                    brand = "Aura Beauty",
                    category = "Rostro",
                    tone = "Durazno",
                    price = 46900.0,
                    stock = 12,
                    isActive = true,
                    description = "Ilumina y aporta un rubor radiante y natural para realzar tus pómulos todo el día.",
                    rating = 4.9,
                    reviewsCount = 95,
                    isFavorite = true
                ),
                Product(
                    id = "p3",
                    name = "Máscara Bold",
                    brand = "Aura Beauty",
                    category = "Ojos",
                    tone = "Negro Intenso",
                    price = 36900.0,
                    stock = 6,
                    isActive = true,
                    description = "Pestañas con volumen extremo, definición perfecta y resistencia al agua sin grumos.",
                    rating = 4.7,
                    reviewsCount = 150
                ),
                Product(
                    id = "p4",
                    name = "Base Hydra Glow",
                    brand = "Aura Beauty",
                    category = "Piel",
                    tone = "Beige Claro",
                    price = 54900.0,
                    stock = 0,
                    isActive = false,
                    description = "Cobertura media construible con acabado satinado e hidratación continua de 24 horas.",
                    rating = 4.6,
                    reviewsCount = 80
                ),
                Product(
                    id = "p5",
                    name = "Fijador Matte Fix",
                    brand = "Aura Beauty",
                    category = "Rostro",
                    tone = "Translúcido",
                    price = 32000.0,
                    stock = 18,
                    isActive = true,
                    description = "Bruma fijadora ultra fina que controla el brillo y mantiene tu maquillaje impecable.",
                    rating = 4.8,
                    reviewsCount = 64
                )
            )
        )

        // 3. Initial Cart Items (matching Screen 4 mockup: Labial Velvet x1, Rubor Glow x1)
        cartItemsList.add(CartItem(productsList[0], 1))
        cartItemsList.add(CartItem(productsList[1], 1))

        // 4. Initial Orders (matching Screen 6 seller mockup)
        ordersList.addAll(
            listOf(
                Order("o1", "Pedido #1048", "12 abr. 2026 - 10:24", "pendiente", "Ana Torres", 3, 126700.0),
                Order("o2", "Pedido #1047", "11 abr. 2026 - 16:30", "en_preparacion", "Mariana López", 2, 86800.0),
                Order("o3", "Pedido #1046", "10 abr. 2026 - 09:15", "enviado", "Camila Rojas", 1, 39900.0)
            )
        )

        // 5. Initial Audit Logs (matching Screen 6 admin mockup)
        auditLogsList.addAll(
            listOf(
                AuditLog("l1", "12 abr 2026 - 10:24", "Inicio de sesión", "Valentina", "Administrador", "login"),
                AuditLog("l2", "12 abr 2026 - 09:17", "Usuario bloqueado", "Laura Gómez", "Compradora", "blocked"),
                AuditLog("l3", "12 abr 2026 - 08:45", "Producto actualizado", "Labial Velvet", "Administrador", "product_update"),
                AuditLog("l4", "12 abr 2026 - 07:30", "Pago procesado", "#ORD-4582", "Sistema", "payment"),
                AuditLog("l5", "12 abr 2026 - 06:12", "Nuevo usuario", "Carlos Méndez", "Vendedor", "new_user")
            )
        )
    }

    // --- Users methods ---
    fun getUsers(): List<User> = usersList.toList()

    fun getUserById(id: String): User? = usersList.find { it.id == id }

    fun toggleUserBlock(userId: String): Boolean {
        val user = getUserById(userId) ?: return false
        val newStatus = if (user.isBlocked()) "activo" else "bloqueado"
        user.status = newStatus
        addAuditLog(
            actionTitle = if (newStatus == "bloqueado") "Usuario bloqueado" else "Usuario desbloqueado",
            actorName = user.name,
            actorRole = user.getDisplayRole(),
            actionType = "blocked"
        )
        return true
    }

    fun updateUser(id: String, name: String, email: String, role: String, status: String) {
        val user = getUserById(id) ?: return
        user.name = name
        user.email = email
        user.role = role
        user.status = status
    }

    // --- Products methods ---
    fun getProducts(): List<Product> = productsList.toList()

    fun getProductById(id: String): Product? = productsList.find { it.id == id }

    fun addProduct(name: String, brand: String, category: String, tone: String, price: Double, stock: Int): Product {
        val newProduct = Product(
            id = "p_${System.currentTimeMillis()}",
            name = name,
            brand = brand,
            category = category,
            tone = tone,
            price = price,
            stock = stock,
            isActive = stock > 0
        )
        productsList.add(0, newProduct)
        addAuditLog("Producto creado", newProduct.name, "Vendedor", "product_update")
        return newProduct
    }

    fun updateProduct(id: String, name: String, brand: String, category: String, tone: String, price: Double, stock: Int) {
        val product = getProductById(id) ?: return
        product.name = name
        product.brand = brand
        product.category = category
        product.tone = tone
        product.price = price
        product.stock = stock
        product.isActive = stock > 0
        addAuditLog("Producto actualizado", product.name, "Administrador", "product_update")
    }

    fun toggleProductActive(id: String): Boolean {
        val product = getProductById(id) ?: return false
        product.isActive = !product.isActive
        return product.isActive
    }

    // --- Cart methods ---
    fun getCartItems(): List<CartItem> = cartItemsList.toList()

    fun addToCart(product: Product) {
        val existing = cartItemsList.find { it.product.id == product.id }
        if (existing != null) {
            existing.quantity++
        } else {
            cartItemsList.add(CartItem(product, 1))
        }
    }

    fun updateCartQuantity(productId: String, delta: Int) {
        val item = cartItemsList.find { it.product.id == productId } ?: return
        item.quantity += delta
        if (item.quantity <= 0) {
            cartItemsList.remove(item)
        }
    }

    fun removeFromCart(productId: String) {
        cartItemsList.removeAll { it.product.id == productId }
    }

    fun clearCart() {
        cartItemsList.clear()
    }

    fun getCartSubtotal(): Double = cartItemsList.sumOf { it.subtotal }

    fun getCartShipping(): Double = if (cartItemsList.isNotEmpty()) 6900.0 else 0.0

    fun getCartTotal(): Double = getCartSubtotal() + getCartShipping()

    // --- Orders methods ---
    fun getOrders(): List<Order> = ordersList.toList()

    fun createOrderFromCart(clientName: String): Order {
        val newOrder = Order(
            id = "o_${System.currentTimeMillis()}",
            orderNumber = "Pedido #${1049 + ordersList.size}",
            date = "Hoy - En camino",
            status = "en_camino",
            clientName = clientName,
            itemsCount = cartItemsList.sumOf { it.quantity },
            total = getCartTotal()
        )
        ordersList.add(0, newOrder)
        addAuditLog("Pago procesado", newOrder.orderNumber, "Pasarela Stripe", "payment")
        clearCart()
        return newOrder
    }

    // --- Audit Logs ---
    fun getAuditLogs(): List<AuditLog> = auditLogsList.toList()

    fun addAuditLog(actionTitle: String, actorName: String, actorRole: String, actionType: String) {
        auditLogsList.add(
            0,
            AuditLog(
                id = "log_${System.currentTimeMillis()}",
                timestamp = "Hoy - Reciente",
                actionTitle = actionTitle,
                actorName = actorName,
                actorRole = actorRole,
                actionType = actionType
            )
        )
    }

    // --- Tones for Product Detail ---
    fun getSampleTones(): List<ToneOption> {
        return listOf(
            ToneOption("Rosa Nude", "#C97A7E", isSelected = true),
            ToneOption("Peach Glow", "#E88B74", isSelected = false),
            ToneOption("Terracotta", "#BD5B48", isSelected = false),
            ToneOption("Crimson Velvet", "#8C263E", isSelected = false),
            ToneOption("Deep Berry", "#5B1D2E", isSelected = false)
        )
    }
}
