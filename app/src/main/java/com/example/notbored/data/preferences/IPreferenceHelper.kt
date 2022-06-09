package com.example.notbored.data.preferences

interface IPreferenceHelper {
    fun setParticipants(quantity: Int)
    fun setCategory(category: String)
    fun getParticipants(): Int
    fun getCategory(): String
    fun clearPrefs()
}