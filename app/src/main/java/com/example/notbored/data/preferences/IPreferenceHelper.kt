package com.example.notbored.data.preferences

interface IPreferenceHelper {
    fun setParticipants(quantity:String)
    fun getParticipants(): String
    fun clearPrefs()
}