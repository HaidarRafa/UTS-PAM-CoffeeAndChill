package lat.pam.uts_kopinyemil_haidarrafaradinka

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RiwayatAdapter(
    private val orderList: List<OrderHistoryItem>
) : RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder>() {

    class RiwayatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHeaderTitle: TextView = itemView.findViewById(R.id.tvOrderHeaderTitle)
        val tvStatus: TextView = itemView.findViewById(R.id.tvOrderStatus)
        val tvDate: TextView = itemView.findViewById(R.id.tvOrderDate)
        val llItemsList: LinearLayout = itemView.findViewById(R.id.llRiwayatItemsList)
        val tvTotalPrice: TextView = itemView.findViewById(R.id.tvOrderTotalPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_riwayat_order, parent, false)
        return RiwayatViewHolder(view)
    }

    override fun onBindViewHolder(holder: RiwayatViewHolder, position: Int) {
        val order = orderList[position]
        holder.tvHeaderTitle.text = order.title
        holder.tvDate.text = order.date
        holder.tvTotalPrice.text = order.totalPrice
        holder.tvStatus.text = order.status

        holder.llItemsList.removeAllViews()
        val inflater = LayoutInflater.from(holder.itemView.context)
        for (itemPair in order.itemList) {
            val rowView = inflater.inflate(R.layout.item_checkout_order_row, holder.llItemsList, false)
            rowView.findViewById<TextView>(R.id.tvRowItemName)?.text = itemPair.first
            rowView.findViewById<TextView>(R.id.tvRowItemPrice)?.text = itemPair.second
            holder.llItemsList.addView(rowView)
        }
    }

    override fun getItemCount(): Int = orderList.size
}
