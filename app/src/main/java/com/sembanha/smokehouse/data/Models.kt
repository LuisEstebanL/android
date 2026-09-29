package com.sembanha.smokehouse.data

data class Dish(
    val id: String,
    val name: String,
    val category: String,
    val price: Double,
    val imageAsset: String,
    val fallbackUrl: String,
    val badges: List<String>,
    val serving: String,
    val weight: String,
    val description: String,
    val acompanhamentos: List<SideOption> = emptyList(),
    val molhos: List<SauceOption> = emptyList()
)

data class SideOption(
    val id: String,
    val name: String,
    val desc: String,
    val included: Boolean = true
)

data class SauceOption(
    val id: String,
    val name: String,
    val price: Double = 0.0
)

data class OrderItem(
    val id: String,
    val dishId: String,
    val name: String,
    val tag: String,
    val servingType: String,
    val unitPrice: Double,
    val qty: Int,
    val deliveredTime: String,
    val status: String = "delivered"
) {
    val totalPrice: Double get() = unitPrice * qty
}

data class Stamp(
    val number: Int,
    val date: String,
    val table: String,
    val server: String,
    val completed: Boolean
)

data class MuralItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageAsset: String,
    val tag: String
)

data class Category(
    val id: String,
    val label: String,
    val icon: String
)

enum class PaymentMethod(val label: String, val discountPercent: Double) {
    PIX("PIX Instantâneo", 0.05),
    CREDIT_CARD("Cartão de Crédito / Débito", 0.0),
    DIGITAL_WALLET("Carteiras Digitais", 0.0)
}
