package com.example.thuchanh6.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsManager(private val context: Context) {

    companion object {
        private val COLOR_KEY = stringPreferencesKey("bg_color")
    }

    val colorFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[COLOR_KEY] ?: "white"
    }

    suspend fun saveColor(color: String) {
        context.dataStore.edit { prefs ->
            prefs[COLOR_KEY] = color
        }
    }
}
