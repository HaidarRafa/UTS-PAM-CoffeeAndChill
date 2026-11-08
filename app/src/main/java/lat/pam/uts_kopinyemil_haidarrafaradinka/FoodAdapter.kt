package lat.pam.uts_kopinyemil_haidarrafaradinka

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lat.pam.uts_kopinyemil_haidarrafaradinka.databinding.ItemMenuFoodBinding

class FoodAdapter(
    private val itemList: List<MenuItem>,
    private val onCartUpdated: () -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(val binding: ItemMenuFoodBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemMenuFoodBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = itemList[position]
        val currentQty = OrderManager.getQuantity(item)

        with(holder.binding) {
            tvFoodName.text = item.name
            tvFoodDesc.text = "• ${item.description}"
            tvFoodPrice.text = "• ${item.price}"
            ivFoodImage.setImageResource(item.imageResId)

            updateQuantityUI(holder, currentQty)

            btnPesan.setOnClickListener {
                OrderManager.setQuantity(item, 1)
                updateQuantityUI(holder, 1)
                onCartUpdated()
            }

            btnPlus.setOnClickListener {
                val qty = OrderManager.getQuantity(item) + 1
                OrderManager.setQuantity(item, qty)
                updateQuantityUI(holder, qty)
                onCartUpdated()
            }

            btnMinus.setOnClickListener {
                val qty = (OrderManager.getQuantity(item) - 1).coerceAtLeast(0)
                OrderManager.setQuantity(item, qty)
                updateQuantityUI(holder, qty)
                onCartUpdated()
            }
        }
    }

    private fun updateQuantityUI(holder: FoodViewHolder, qty: Int) {
        with(holder.binding) {
            if (qty > 0) {
                btnPesan.visibility = View.GONE
                llQuantityControl.visibility = View.VISIBLE
                tvQuantity.text = qty.toString()
            } else {
                btnPesan.visibility = View.VISIBLE
                llQuantityControl.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = itemList.size
}
