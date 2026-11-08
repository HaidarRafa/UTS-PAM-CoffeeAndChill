package lat.pam.uts_kopinyemil_haidarrafaradinka

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import lat.pam.uts_kopinyemil_haidarrafaradinka.databinding.FragmentOrderBinding
import java.util.Locale

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private var foodName: String? = null
    private var foodPrice: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            foodName = it.getString(ARG_FOOD_NAME)
            foodPrice = it.getString(ARG_FOOD_PRICE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pre-fill user profile and address
        val currentName = UserSession.getDisplayName(requireContext())
        val currentAddress = UserSession.getAddress(requireContext())
        val currentPatokan = UserSession.getPatokan(requireContext())

        binding.etNamaLengkap.setText(currentName)
        binding.etAlamat.setText(currentAddress)
        binding.etPatokan.setText(currentPatokan)

        val activeItems = OrderManager.getActiveCartItems()
        binding.llCheckoutItemsList.removeAllViews()

        if (activeItems.isNotEmpty()) {
            for (cartItem in activeItems) {
                val rowView = layoutInflater.inflate(R.layout.item_checkout_order_row, binding.llCheckoutItemsList, false)
                val tvName = rowView.findViewById<TextView>(R.id.tvRowItemName)
                val tvPrice = rowView.findViewById<TextView>(R.id.tvRowItemPrice)

                val itemPriceClean = cartItem.menuItem.price.replace("Rp", "").replace(".", "").trim().toIntOrNull() ?: 0
                val itemSubtotal = itemPriceClean * cartItem.quantity

                tvName.text = "• ${cartItem.menuItem.name} (${cartItem.quantity}x)"
                tvPrice.text = "Rp ${String.format(Locale.GERMAN, "%,d", itemSubtotal).replace(',', '.')}"

                binding.llCheckoutItemsList.addView(rowView)
            }
            binding.tvCheckoutTotal.text = OrderManager.getFormattedTotalPrice()
            binding.cardSelectedItem.visibility = View.VISIBLE
        } else if (!foodName.isNullOrBlank()) {
            val rowView = layoutInflater.inflate(R.layout.item_checkout_order_row, binding.llCheckoutItemsList, false)
            val tvName = rowView.findViewById<TextView>(R.id.tvRowItemName)
            val tvPrice = rowView.findViewById<TextView>(R.id.tvRowItemPrice)

            tvName.text = "• $foodName (1x)"
            tvPrice.text = foodPrice ?: ""

            binding.llCheckoutItemsList.addView(rowView)
            binding.tvCheckoutTotal.text = foodPrice ?: ""
            binding.cardSelectedItem.visibility = View.VISIBLE
        } else {
            binding.cardSelectedItem.visibility = View.GONE
        }

        binding.btnOrderKirim.setOnClickListener {
            var nama = binding.etNamaLengkap.text.toString().trim()
            var alamat = binding.etAlamat.text.toString().trim()
            var patokan = binding.etPatokan.text.toString().trim()

            if (nama.isBlank()) {
                nama = currentName
            }
            if (alamat.isBlank()) {
                alamat = currentAddress
            }
            if (patokan.isBlank()) {
                patokan = currentPatokan
            }

            // Save updated address to session
            UserSession.updateProfile(requireContext(), nama, alamat, patokan)

            // Checkout current cart & add to Order History!
            OrderManager.checkoutCurrentCart()

            // Navigate to ConfirmationActivity
            val intent = Intent(requireContext(), ConfirmationActivity::class.java)
            intent.putExtra("USER_NAME", nama)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_FOOD_NAME = "arg_food_name"
        private const val ARG_FOOD_PRICE = "arg_food_price"

        fun newInstance(foodName: String, foodPrice: String): OrderFragment {
            val fragment = OrderFragment()
            val args = Bundle().apply {
                putString(ARG_FOOD_NAME, foodName)
                putString(ARG_FOOD_PRICE, foodPrice)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
