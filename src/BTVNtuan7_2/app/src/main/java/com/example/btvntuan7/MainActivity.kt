package com.example.btvntuan7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.btvntuan7.Screen.Screen1
import com.example.btvntuan7.Screen.Screen2
import com.example.btvntuan7.ViewModel.TaskViewModel
import com.example.btvntuan7.ui.theme.BTVNtuan7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BTVNtuan7Theme {
                val taskViewModel: TaskViewModel = viewModel(
                    factory = ViewModelProvider.AndroidViewModelFactory(application)
                )
                Surface(modifier = Modifier.fillMaxSize()) {
                    ChangeScreen(taskViewModel)
                }
            }
        }
    }
}

@Composable
fun ChangeScreen(taskViewModel: TaskViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){ Screen1(navController, taskViewModel)}
        composable("screen2"){ Screen2(navController, taskViewModel) }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BTVNtuan7Theme {
    }
}