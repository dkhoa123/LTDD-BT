package com.example.thuchanh6.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thuchanh6.data.SettingsManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val settingsManager: SettingsManager) : ViewModel() {

    val currentColor = settingsManager.colorFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "white")

    fun changeColor(color: String) {
        viewModelScope.launch {
            settingsManager.saveColor(color)
        }
    }
}
