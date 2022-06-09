package com.example.notbored.data.preferences

interface IPreferenceHelper {
    fun setParticipants(quantity: Int)
    fun setCategory(category: String)
    fun setRandom(isRandom: Boolean)
    fun getParticipants(): Int
    fun getCategory(): String
    fun isRandom(): Boolean
    fun clearPrefs()
}