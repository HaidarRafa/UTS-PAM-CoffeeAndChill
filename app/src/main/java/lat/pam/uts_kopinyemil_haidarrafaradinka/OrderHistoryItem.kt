package lat.pam.uts_kopinyemil_haidarrafaradinka

data class OrderHistoryItem(
    val title: String = "Pesanan Kopi & Nyemil",
    val date: String,
    val itemList: List<Pair<String, String>>,
    val totalPrice: String,
    val status: String = "Sukses"
)
