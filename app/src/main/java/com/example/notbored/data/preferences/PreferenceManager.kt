package com.example.notbored.data.preferences

import android.content.Context
import android.content.SharedPreferences

open class PreferenceManager constructor(context: Context) : IPreferenceHelper {
    private val PREFS_NAME = "SharedPreferenceDemo"
    private var preferences: SharedPreferences
    init {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    override fun setParticipants(quantity: Int) {
        preferences.edit().putInt(PARTICIPANTS , quantity).apply()
    }

    override fun setCategory(category: String) {
        preferences.edit().putString("category" , category).apply()
    }

    override fun setRandom(isRandom: Boolean) {
        preferences.edit().putBoolean("isRandom" , isRandom).apply()
    }

    override fun getParticipants(): Int {
        return preferences.getInt(PARTICIPANTS, 0) ?: 0
    }

    override fun getCategory(): String {
        return preferences.getString("category", "") ?: ""
    }

    override fun isRandom(): Boolean {
        return preferences.getBoolean("isRandom", false)
    }
    override fun clearPrefs() {
        preferences.edit().clear().apply()
    }

    companion object {
        const val PARTICIPANTS = "participant"
    }
}