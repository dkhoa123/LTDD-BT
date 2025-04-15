package com.example.thuchanh6.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ColorChangerScreen(navController: NavController,
                       viewModel: MainViewModel) {
    val colorName = viewModel.currentColor.collectAsState().value

    val bgColor = when (colorName) {
        "xanh" -> Color(0xFF79C1FA)
        "hong" -> Color(0xFFD346B7)
        "den" -> Color(0xFF323032)
        else -> Color.White
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if(bgColor == Color.White) {
            Text(
                text = "Setting",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3),
                modifier = Modifier.clickable { navController.navigate("screen2") }
            )
            Text(
                text = "Choosing the right theme sets the tone and personality of your app",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF2196F3)
            )
        }else{
            Text(
                text = "Setting",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.clickable { navController.navigate("screen2") }
            )
            Text(
                text = "Choosing the right theme sets the tone and personality of your app",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (bgColor == Color(0xFF79C1FA)) {
                Button(
                    onClick = { viewModel.changeColor("xanh") },
                    modifier = Modifier.padding(8.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2196F3)// Thay đổi màu nền
                    )
                ) {}
            } else {
                Button(
                    onClick = { viewModel.changeColor("xanh") },
                    modifier = Modifier.padding(8.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF79C1FA)// Thay đổi màu nền
                    )
                ) {}
            }
            Spacer(modifier = Modifier.weight(1f))
            if(bgColor == Color(0xFFD346B7)) {
                    Button(
                        onClick = { viewModel.changeColor("hong") },
                        modifier = Modifier.padding(8.dp)
                            .border(4.dp, Color.Black, shape = RoundedCornerShape(4.dp)),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD346B7)// Thay đổi màu nền
                        )
                    ) {}
                }else {
                Button(
                    onClick = { viewModel.changeColor("hong") },
                    modifier = Modifier.padding(8.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD346B7)// Thay đổi màu nền
                    )
                ) {}
            }
            Spacer(modifier = Modifier.weight(1f))
            if (bgColor == Color(0xFF323032)){
                Button(
                    onClick = { viewModel.changeColor("den") },
                    modifier = Modifier.padding(8.dp)
                        .border(4.dp, Color.Black, shape = RoundedCornerShape(4.dp)),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF323032)// Thay đổi màu nền
                    ),
                    contentPadding = PaddingValues(0.dp)

                ) {}
                }else{
                Button(
                    onClick = { viewModel.changeColor("den") },
                    modifier = Modifier.padding(8.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF323032)// Thay đổi màu nền
                    ),
                    contentPadding = PaddingValues(0.dp)

                ) {}
            }
        }
    }
}
