package lat.pam.uts_kopinyemil_haidarrafaradinka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class RiwayatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_riwayat, container, false)
        val containerLayout = view.findViewById<LinearLayout>(R.id.llHistoryContainer)

        val historyList = OrderManager.getOrderHistory()
        if (containerLayout != null) {
            containerLayout.removeAllViews()

            for (order in historyList) {
                val cardView = inflater.inflate(R.layout.item_riwayat_order, containerLayout, false)
                cardView.findViewById<TextView>(R.id.tvOrderHeaderTitle)?.text = order.title
                cardView.findViewById<TextView>(R.id.tvOrderStatus)?.text = order.status
                cardView.findViewById<TextView>(R.id.tvOrderDate)?.text = order.date
                cardView.findViewById<TextView>(R.id.tvOrderTotalPrice)?.text = order.totalPrice

                val itemsContainer = cardView.findViewById<LinearLayout>(R.id.llRiwayatItemsList)
                itemsContainer?.removeAllViews()

                for (itemPair in order.itemList) {
                    val rowView = inflater.inflate(R.layout.item_checkout_order_row, itemsContainer, false)
                    rowView.findViewById<TextView>(R.id.tvRowItemName)?.text = itemPair.first
                    rowView.findViewById<TextView>(R.id.tvRowItemPrice)?.text = itemPair.second
                    itemsContainer?.addView(rowView)
                }

                containerLayout.addView(cardView)
            }
        }

        return view
    }
}
