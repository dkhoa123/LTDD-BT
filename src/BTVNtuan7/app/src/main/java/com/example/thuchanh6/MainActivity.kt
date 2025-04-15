package com.example.thuchanh6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thuchanh6.data.SettingsManager
import com.example.thuchanh6.screen.ColorChangerScreen
import com.example.thuchanh6.screen.MainViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thuchanh6.screen.DarkScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val viewModel: MainViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        val settingsManager = SettingsManager(context)
                        return MainViewModel(settingsManager) as T
                    }
                }
            )
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home"){ColorChangerScreen(navController,viewModel)}
                composable("screen2"){ DarkScreen(navController)}
            }
        }
    }

}