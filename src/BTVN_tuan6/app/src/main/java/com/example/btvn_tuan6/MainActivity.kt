package com.example.btvn_tuan6

import android.app.FragmentManager.BackStackEntry
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.btvn_tuan6.ui.theme.BTVN_tuan6Theme
import com.example.btvn_tuan6.view.Screen1
import com.example.btvn_tuan6.view.Screen2
import com.example.btvn_tuan6.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BTVN_tuan6Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                val viewModel = TaskViewModel()
                    Change2Screen(viewModel)

                }
            }
        }
    }
}

@Composable
fun Change2Screen(viewModel: TaskViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){

        composable("home"){ Screen1(navController, viewModel) }
        composable("screen2/{taskId}"){BackStackEntry ->
            val taskId = BackStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: 0
            Screen2(navController,viewModel,taskId) }
    }
}
