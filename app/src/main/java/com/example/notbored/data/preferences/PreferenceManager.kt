package com.example.notbored.data.preferences

import android.content.Context
import android.content.SharedPreferences

open class PreferenceManager constructor(context: Context) : IPreferenceHelper {
    private val PREFS_NAME = "SharedPreferenceDemo"
    private var preferences: SharedPreferences
    init {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    override fun setParticipants(quantity: String) {
        preferences.edit().putString("participant" , quantity).apply()
    }

    override fun getParticipants(): String {
        return preferences.getString("participant", "") ?: ""
    }

    override fun clearPrefs() {
        preferences.edit().clear().apply()
    }

    companion object {
        const val PARTICIPANTS = "participant"
    }
}