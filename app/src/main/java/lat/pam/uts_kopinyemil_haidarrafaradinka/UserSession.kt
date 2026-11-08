package lat.pam.uts_kopinyemil_haidarrafaradinka

import android.content.Context
import android.content.SharedPreferences

object UserSession {
    private const val PREF_NAME = "kopi_nyemil_prefs"
    private const val KEY_NAME = "user_name"
    private const val KEY_USERNAME = "user_username"
    private const val KEY_ADDRESS = "user_address"
    private const val KEY_PATOKAN = "user_patokan"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveUser(context: Context, name: String, username: String) {
        getPrefs(context).edit().apply {
            putString(KEY_NAME, name)
            putString(KEY_USERNAME, username)
            putBoolean(KEY_IS_LOGGED_IN, true)
            apply()
        }
    }

    fun updateProfile(context: Context, name: String, address: String, patokan: String) {
        getPrefs(context).edit().apply {
            putString(KEY_NAME, name)
            putString(KEY_ADDRESS, address)
            putString(KEY_PATOKAN, patokan)
            apply()
        }
    }

    fun getDisplayName(context: Context): String {
        val name = getPrefs(context).getString(KEY_NAME, null)
        return if (!name.isNullOrBlank()) name else "Haidar Rafa"
    }

    fun getAddress(context: Context): String {
        val addr = getPrefs(context).getString(KEY_ADDRESS, null)
        return if (!addr.isNullOrBlank()) addr else "Jl. Pajajaran No. 123, Bogor"
    }

    fun getPatokan(context: Context): String {
        val pat = getPrefs(context).getString(KEY_PATOKAN, null)
        return if (!pat.isNullOrBlank()) pat else "Dekat Toko Serba Ada"
    }
}
