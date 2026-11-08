package lat.pam.uts_kopinyemil_haidarrafaradinka

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object OrderManager {
    private val cartMap = mutableMapOf<Int, CartItem>()

    private val orderHistoryList = mutableListOf<OrderHistoryItem>()

    fun getQuantity(menuItem: MenuItem): Int {
        return cartMap[menuItem.id]?.quantity ?: 0
    }

    fun setQuantity(menuItem: MenuItem, quantity: Int) {
        if (quantity > 0) {
            cartMap[menuItem.id] = CartItem(menuItem, quantity)
        } else {
            cartMap.remove(menuItem.id)
        }
    }

    fun getActiveCartItems(): List<CartItem> {
        return cartMap.values.filter { it.quantity > 0 }
    }

    fun getTotalItemCount(): Int {
        return cartMap.values.sumOf { it.quantity }
    }

    fun getTotalPriceAmount(): Int {
        var total = 0
        for (cartItem in cartMap.values) {
            val priceClean = cartItem.menuItem.price
                .replace("Rp", "")
                .replace(".", "")
                .trim()
                .toIntOrNull() ?: 0
            total += priceClean * cartItem.quantity
        }
        return total
    }

    fun getFormattedTotalPrice(): String {
        val total = getTotalPriceAmount()
        return "Rp ${String.format(Locale.GERMAN, "%,d", total).replace(',', '.')}"
    }

    fun clearCart() {
        cartMap.clear()
    }

    fun getOrderHistory(): List<OrderHistoryItem> {
        return orderHistoryList
    }

    fun checkoutCurrentCart(): OrderHistoryItem? {
        val activeItems = getActiveCartItems()
        if (activeItems.isEmpty()) return null

        val itemsList = mutableListOf<Pair<String, String>>()
        for (item in activeItems) {
            val itemPriceClean = item.menuItem.price
                .replace("Rp", "")
                .replace(".", "")
                .trim()
                .toIntOrNull() ?: 0
            val subtotal = itemPriceClean * item.quantity
            val formattedSubtotal = "Rp ${String.format(Locale.GERMAN, "%,d", subtotal).replace(',', '.')}"

            itemsList.add(Pair("• ${item.menuItem.name} (${item.quantity}x)", formattedSubtotal))
        }

        val dateFormat = SimpleDateFormat("'Hari ini,' HH:mm 'WIB'", Locale.getDefault())
        val currentTime = dateFormat.format(Date())

        val newOrder = OrderHistoryItem(
            title = "Pesanan Kopi & Nyemil",
            date = currentTime,
            itemList = itemsList,
            totalPrice = getFormattedTotalPrice(),
            status = "Sukses"
        )

        orderHistoryList.add(0, newOrder) // Add to top of list
        clearCart()
        return newOrder
    }
}
