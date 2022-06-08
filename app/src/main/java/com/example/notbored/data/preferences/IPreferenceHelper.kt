package com.example.notbored.data.preferences

interface IPreferenceHelper {
    fun setParticipants(quantity:String)
    fun setCategory(Category: String)
    fun getParticipants(): String
    fun getCategory(): String
    fun clearPrefs()
}