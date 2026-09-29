package com.sembanha.smokehouse.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sembanha.smokehouse.data.Dish
import com.sembanha.smokehouse.data.MockData
import com.sembanha.smokehouse.data.MuralItem
import com.sembanha.smokehouse.data.OrderItem
import com.sembanha.smokehouse.data.PaymentMethod
import com.sembanha.smokehouse.data.Stamp

class RestaurantViewModel : ViewModel() {

    var activeTab by mutableStateOf("menu")
    var selectedCategory by mutableStateOf("todos")
    var searchQuery by mutableStateOf("")

    val orders = mutableStateListOf<OrderItem>().apply {
        addAll(MockData.initialOrders)
    }

    val stamps = mutableStateListOf<Stamp>().apply {
        addAll(MockData.initialStamps)
    }

    val mural = mutableStateListOf<MuralItem>().apply {
        addAll(MockData.muralMemories)
    }

    var selectedDishForDetail by mutableStateOf<Dish?>(null)
    var isWaiterDialogOpen by mutableStateOf(false)

    var splitCount by mutableIntStateOf(4)
    var tipPercent by mutableIntStateOf(12)
    var paymentMethod by mutableStateOf(PaymentMethod.PIX)
    var isPaid by mutableStateOf(false)
    var toastMessage by mutableStateOf<String?>(null)

    val stampsCount: Int
        get() = stamps.count { it.completed }

    val subtotal: Double
        get() = orders.sumOf { it.totalPrice }

    val tipAmount: Double
        get() = subtotal * (tipPercent / 100.0)

    val discountAmount: Double
        get() = if (paymentMethod == PaymentMethod.PIX) subtotal * 0.05 else 0.0

    val finalTotal: Double
        get() = (subtotal + tipAmount - discountAmount).coerceAtLeast(0.0)

    val perPersonTotal: Double
        get() = if (splitCount > 0) finalTotal / splitCount else finalTotal

    val totalItemsCount: Int
        get() = orders.sumOf { it.qty }

    fun selectCategory(catId: String) {
        selectedCategory = catId
    }

    fun updateSearch(query: String) {
        searchQuery = query
    }

    fun openDishDetail(dish: Dish) {
        selectedDishForDetail = dish
    }

    fun closeDishDetail() {
        selectedDishForDetail = null
    }

    fun addDishToOrder(dish: Dish, sideChoice: String?, sauces: List<String>, notes: String, qty: Int) {
        val existingIndex = orders.indexOfFirst { it.dishId == dish.id }
        if (existingIndex >= 0) {
            val old = orders[existingIndex]
            orders[existingIndex] = old.copy(qty = old.qty + qty)
        } else {
            orders.add(
                OrderItem(
                    id = "ord-${System.currentTimeMillis()}",
                    dishId = dish.id,
                    name = dish.name,
                    tag = sideChoice?.let { "Acomp: $it" } ?: "Prato Principal",
                    servingType = dish.serving,
                    unitPrice = dish.price,
                    qty = qty,
                    deliveredTime = "Pedido agora",
                    status = "preparing"
                )
            )
        }
        closeDishDetail()
        showToast("🔥 ${qty}x ${dish.name} adicionado à comanda!")
    }

    fun incrementQty(itemId: String) {
        val index = orders.indexOfFirst { it.id == itemId }
        if (index >= 0) {
            val item = orders[index]
            orders[index] = item.copy(qty = item.qty + 1)
        }
    }

    fun decrementQty(itemId: String) {
        val index = orders.indexOfFirst { it.id == itemId }
        if (index >= 0) {
            val item = orders[index]
            if (item.qty > 1) {
                orders[index] = item.copy(qty = item.qty - 1)
            } else {
                orders.removeAt(index)
            }
        }
    }

    fun processPayment() {
        isPaid = true
        // Unlock 8th stamp
        val lastIndex = stamps.indexOfLast { it.number == 8 }
        if (lastIndex >= 0) {
            stamps[lastIndex] = stamps[lastIndex].copy(completed = true)
        }
        showToast("🎉 Pagamento de R$ %.2f aprovado! 8º Selo Conquistado!".format(finalTotal))
        activeTab = "camera"
    }

    fun saveCapturedPhoto(title: String, tag: String) {
        mural.add(
            0,
            MuralItem(
                id = "m-${System.currentTimeMillis()}",
                title = title,
                subtitle = "Mesa 14 · Salão Principal",
                date = "Hoje na Brasa",
                imageAsset = "file:///android_asset/images/toast-friends.jpg",
                tag = tag
            )
        )
        // Ensure 8th stamp is marked complete
        val lastIndex = stamps.indexOfLast { it.number == 8 }
        if (lastIndex >= 0) {
            stamps[lastIndex] = stamps[lastIndex].copy(completed = true)
        }
        showToast("🎖 Foto eternizada no Mural e 8º Selo carimbado!")
        activeTab = "passport"
    }

    fun showToast(message: String) {
        toastMessage = message
    }

    fun clearToast() {
        toastMessage = null
    }
}
