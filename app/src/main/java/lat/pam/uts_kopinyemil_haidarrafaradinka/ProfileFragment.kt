package lat.pam.uts_kopinyemil_haidarrafaradinka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import lat.pam.uts_kopinyemil_haidarrafaradinka.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadUserData()

        binding.btnSaveProfile.setOnClickListener {
            val newName = binding.etProfileNama.text.toString().trim()
            val newAddress = binding.etProfileAlamat.text.toString().trim()
            val newPatokan = binding.etProfilePatokan.text.toString().trim()

            if (newName.isNotEmpty()) {
                UserSession.updateProfile(requireContext(), newName, newAddress, newPatokan)
                binding.tvGreetingProfile.text = getString(R.string.greeting_format, newName)
                Toast.makeText(requireContext(), "Profile & Alamat berhasil disimpan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadUserData() {
        val currentName = UserSession.getDisplayName(requireContext())
        val currentAddress = UserSession.getAddress(requireContext())
        val currentPatokan = UserSession.getPatokan(requireContext())

        binding.tvGreetingProfile.text = getString(R.string.greeting_format, currentName)
        binding.etProfileNama.setText(currentName)
        binding.etProfileAlamat.setText(currentAddress)
        binding.etProfilePatokan.setText(currentPatokan)
    }

    override fun onResume() {
        super.onResume()
        loadUserData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
