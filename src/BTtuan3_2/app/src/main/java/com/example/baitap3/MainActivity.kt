package com.example.baitap3

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.intIntMapOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitap3.ui.theme.Baitap3Theme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Baitap3Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavFourScreen();
                }
            }
        }
    }
@Composable
fun NavFourScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){HomeScreen(navController)}
        composable("screen2"){ Screen2(navController) }
        composable("screen3"){ Screen3(navController) }
        composable("screen4"){ Screen4(navController)}
    }
    }
}

@Composable
fun HomeScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
    ImgScreen1(navController)
        Text1Screen1()
    }
}
@Composable
fun ImgScreen1(navController: NavController){
    Image(painterResource(id = R.drawable.image1),
        contentDescription = "UTH",
        Modifier
            .padding(top = 280.dp)
            .width(68.dp)
            .height(47.dp).clickable { navController.navigate("screen2") }
    )
}
@Composable
fun Text1Screen1(){
        Text(text = "UTH SmartTasks",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            fontFamily = FontFamily.Default,
            color = Color(0xFF006EE9),
            modifier = Modifier.padding(13.dp)
        )
}

@Composable
fun Screen2(navController: NavController){
    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        BtnDotSkip(navController)
        ImgScreen2()
        TextScreen2()
        BtnScreen2(navController)
    }
}
@Composable
fun BtnDotSkip(navController: NavController){
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
        ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {},
                Modifier.size(10.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF005EE9)
                )
            ) {}
            Button(
                onClick = {navController.navigate("screen3")},
                Modifier.size(10.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEEF5FD)
                )
            ) {}
            Button(
                onClick = {navController.navigate("screen4")},
                Modifier.size(10.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEEF5FD)
                )
            ) {}
        }
        TextButton(onClick = {navController.navigate("home")}){
            Text(text = "Skip",
                fontSize = 12.sp,
                color = Color(0xFF006EE9))
        }
    }
}
@Composable
fun ImgScreen2(){
    Image(painterResource(id=R.drawable.image2),
        contentDescription = "ảnh 2",
        modifier = Modifier
            .padding(top = 70.dp)
            .width(212.dp)
            .height(174.dp))
}
@Composable
fun TextScreen2(){
    Column(modifier = Modifier.height(150.dp)) {
        Text(text ="Easy Time Management",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=8.dp))
        Text(text = "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first ",
            textAlign = TextAlign.Center)
    }
}
@Composable
fun BtnScreen2(navController: NavController){
    Button(onClick = {navController.navigate("screen3")},Modifier.fillMaxWidth().padding(top = 80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2196F3)
        )
    ){
        Text(text="Next",
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun Screen3(navController: NavController){
    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        BtnDotSkip2(navController)
        ImgScreen3()
        TextScreen3()
        BtnScreen3(navController)
    }
}
@Composable
fun BtnDotSkip2(navController: NavController){
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {navController.navigate("screen2")},
                Modifier.size(10.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEEF5FD)
                )
            ) {}
            Button(
                onClick = {},
                Modifier.size(10.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF005EE9)
                )
            ) {}
            Button(
                onClick = {navController.navigate("screen4")},
                Modifier.size(10.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEEF5FD)
                )
            ) {}
        }
        TextButton(onClick = {navController.navigate("home")}){
            Text(text = "Skip",
                fontSize = 12.sp,
                color = Color(0xFF006EE9))
        }
    }
}

@Composable
fun ImgScreen3(){
    Image(painterResource(id=R.drawable.image3),
        contentDescription = "ảnh 2",
        modifier = Modifier
            .padding(top = 70.dp)
            .width(212.dp)
            .height(174.dp))
}
@Composable
fun TextScreen3(){
    Column(modifier = Modifier.height(150.dp)) {
        Text(text ="Increase Work Effectiveness",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=8.dp))
        Text(text = "Time management and the determination of more important tasks will give your job statistics better and always improve.",
            textAlign = TextAlign.Center)
    }
}
@Composable
fun BtnScreen3(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth().padding(top = 80.dp),
        verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier
                .size(37.dp)
                .clip(CircleShape)
                .background(Color(0xFF2196F3))) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .clickable { navController.navigate("screen2") }

                )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {navController.navigate("screen4")},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            )
        ) {
            Text(
                text = "Next",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun Screen4(navController: NavController){
    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        BtnDotSkip3(navController)
        ImgScreen4()
        TextScreen4()
        BtnScreen4(navController)
    }
}
@Composable
fun BtnDotSkip3(navController: NavController){
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {navController.navigate("screen2")},
                Modifier.size(10.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEEF5FD)
                )
            ) {}
            Button(
                onClick = {navController.navigate("screen3")},
                Modifier.size(10.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEEF5FD)
                )
            ) {}
            Button(
                onClick = {},
                Modifier.size(10.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF005EE9)
                )
            ) {}
        }
        TextButton(onClick = {navController.navigate("home")}){
            Text(text = "Skip",
                fontSize = 12.sp,
                color = Color(0xFF006EE9))
        }
    }
}

@Composable
fun ImgScreen4(){
    Image(painterResource(id=R.drawable.image4),
        contentDescription = "ảnh 2",
        modifier = Modifier
            .padding(top = 70.dp)
            .width(212.dp)
            .height(174.dp))
}
@Composable
fun TextScreen4(){
    Column(modifier = Modifier.height(150.dp)) {
        Text(text ="Reminder Notification",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=8.dp))
        Text(text = "The advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set ",
            textAlign = TextAlign.Center)
    }
}
@Composable
fun BtnScreen4(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth().padding(top = 80.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier
            .size(37.dp)
            .clip(CircleShape)
            .background(Color(0xFF2196F3))) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .clickable { navController.navigate("screen3") }
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            )
        ) {
            Text(
                text = "Get Started",
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Baitap3Theme {
        val fakeNavController = rememberNavController()
        Surface(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.fillMaxSize()){
                    HomeScreen(navController = fakeNavController)
                }
                Box(modifier = Modifier.fillMaxSize()){
                    Screen2(navController = fakeNavController)
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    Screen3(navController = fakeNavController)
                }
                Box(modifier = Modifier.fillMaxSize()) {
                  Screen4(navController = fakeNavController)
                }
            }
        }
    }
}