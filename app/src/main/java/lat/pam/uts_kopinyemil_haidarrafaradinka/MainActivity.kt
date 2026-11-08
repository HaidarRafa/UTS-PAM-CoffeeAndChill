package lat.pam.uts_kopinyemil_haidarrafaradinka

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import lat.pam.uts_kopinyemil_haidarrafaradinka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            selectTab(0)
        }

        binding.tabHome.setOnClickListener {
            selectTab(0)
        }

        binding.tabRiwayat.setOnClickListener {
            selectTab(1)
        }

        binding.tabProfile.setOnClickListener {
            selectTab(2)
        }
    }

    fun selectTab(tabIndex: Int) {
        resetTabUI(binding.ivTabHome)
        resetTabUI(binding.ivTabRiwayat)
        resetTabUI(binding.ivTabProfile)

        when (tabIndex) {
            0 -> {
                setActiveTabUI(binding.ivTabHome)
                loadFragment(HomeFragment())
            }
            1 -> {
                setActiveTabUI(binding.ivTabRiwayat)
                loadFragment(RiwayatFragment())
            }
            2 -> {
                setActiveTabUI(binding.ivTabProfile)
                loadFragment(ProfileFragment())
            }
        }
    }

    private fun setActiveTabUI(imageView: ImageView) {
        imageView.setBackgroundResource(R.drawable.bg_nav_active_circle)
        imageView.setColorFilter(Color.parseColor("#FFFFFF"))
    }

    private fun resetTabUI(imageView: ImageView) {
        imageView.background = null
        imageView.setColorFilter(Color.parseColor("#9E8D83"))
    }

    fun openOrderFragment(foodName: String, foodPrice: String) {
        val orderFragment = OrderFragment.newInstance(foodName, foodPrice)
        loadFragment(orderFragment)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
