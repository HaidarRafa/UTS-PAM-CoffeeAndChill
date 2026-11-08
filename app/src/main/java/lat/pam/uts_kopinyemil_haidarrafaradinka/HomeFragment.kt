package lat.pam.uts_kopinyemil_haidarrafaradinka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import lat.pam.uts_kopinyemil_haidarrafaradinka.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val fullFoodList = listOf(
        MenuItem(
            1,
            "Kopi Susu Gula Aren",
            "Espresso, susu, gula aren",
            "Rp 22.000",
            R.drawable.ic_coffee_susu,
            "Coffee"
        ),
        MenuItem(
            2,
            "Cappuccino",
            "Espresso, foam susu lembut",
            "Rp 25.000",
            R.drawable.ic_cappuccino,
            "Coffee"
        ),
        MenuItem(
            3,
            "Matcha Latte",
            "Matcha jepang, susu segar",
            "Rp 27.000",
            R.drawable.ic_matcha,
            "Non-coffee"
        ),
        MenuItem(
            4,
            "Chocolate Float",
            "Cokelat pekat, es krim vanilla",
            "Rp 26.000",
            R.drawable.ic_matcha,
            "Non-coffee"
        ),
        MenuItem(
            5,
            "Fresh Orange Juice",
            "Jeruk peras segar murni",
            "Rp 20.000",
            R.drawable.ic_juice,
            "Juice"
        ),
        MenuItem(
            6,
            "Avocado Juice",
            "Jus alpukat lembut manis",
            "Rp 24.000",
            R.drawable.ic_juice,
            "Juice"
        ),
        MenuItem(
            7,
            "Sourdough Loaf",
            "Roti fermentasi alami",
            "Rp 35.000",
            R.drawable.ic_bread,
            "Makanan"
        ),
        MenuItem(
            8,
            "Donat Cokelat",
            "Donat empuk, glaze cokelat",
            "Rp 12.000",
            R.drawable.ic_donut,
            "Snacks"
        )
    )

    private var currentFilteredList = fullFoodList.toList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = UserSession.getDisplayName(requireContext())
        binding.tvGreeting.text = getString(R.string.greeting_format, name)

        binding.rvFoodList.layoutManager = LinearLayoutManager(requireContext())
        updateAdapterList(fullFoodList)

        setupCategoryChips()

        binding.btnCheckout.setOnClickListener {
            val activeItems = OrderManager.getActiveCartItems()
            if (activeItems.isNotEmpty()) {
                val firstItem = activeItems[0].menuItem
                (activity as? MainActivity)?.openOrderFragment(firstItem.name, firstItem.price)
            }
        }

        updateCartBar()
    }

    private fun setupCategoryChips() {
        binding.chipGroupCategories.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isEmpty()) return@setOnCheckedStateChangeListener

            val selectedChipId = checkedIds[0]
            val filtered = when (selectedChipId) {
                R.id.chipCoffee -> fullFoodList.filter { it.category == "Coffee" }
                R.id.chipNonCoffee -> fullFoodList.filter { it.category == "Non-coffee" }
                R.id.chipJuice -> fullFoodList.filter { it.category == "Juice" }
                R.id.chipMakanan -> fullFoodList.filter { it.category == "Makanan" }
                R.id.chipSnacks -> fullFoodList.filter { it.category == "Snacks" }
                else -> fullFoodList
            }

            binding.tvSectionTitle.text = when (selectedChipId) {
                R.id.chipCoffee -> "Coffee"
                R.id.chipNonCoffee -> "Non-coffee"
                R.id.chipJuice -> "Juice"
                R.id.chipMakanan -> "Food"
                R.id.chipSnacks -> "Snacks"
                else -> "All Menu"
            }

            updateAdapterList(filtered)
        }
    }

    private fun updateAdapterList(list: List<MenuItem>) {
        currentFilteredList = list
        binding.rvFoodList.adapter = FoodAdapter(list) {
            updateCartBar()
        }
    }

    private fun updateCartBar() {
        val totalCount = OrderManager.getTotalItemCount()
        if (totalCount > 0) {
            binding.tvCartCount.text = "$totalCount Item"
            binding.tvCartTotalPrice.text = OrderManager.getFormattedTotalPrice()
            binding.cardCartBar.visibility = View.VISIBLE
        } else {
            binding.cardCartBar.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        val name = UserSession.getDisplayName(requireContext())
        binding.tvGreeting.text = getString(R.string.greeting_format, name)
        binding.rvFoodList.adapter?.notifyDataSetChanged()
        updateCartBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
