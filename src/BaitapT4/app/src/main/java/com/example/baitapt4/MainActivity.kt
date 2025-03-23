package com.example.baitapt4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.baitapt4.ui.theme.BaiTapT4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapT4Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ChangeScreen()
                }
            }
        }
    }
}
@Composable
fun ChangeScreen(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
       composable("home"){ Screen1(navController) }
        composable("screen2"){ Screen2(navController)}
        composable("screen3/{index}"){entry ->
            val index = entry.arguments?.getString("index")?.toIntOrNull()?:0
            Screen3(index, navController) }

    }
}


@Composable
fun Screen1(navController: NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally) {
        ShowImg()
        ShowText()
        ShowButton(navController)
    }
}
@Composable
fun ShowImg(){
    Image(
        painterResource(id =R.drawable.image),
        contentDescription = "Ảnh",
        modifier = Modifier
            .padding(top = 99.dp)
            .width(144.dp)
            .height(155.dp)
    )
}
@Composable
fun ShowText(){
    Column(modifier = Modifier.padding(top = 37.dp)) {
        Text(text = "Navigation",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(text = "is a framework that simplifies the implementation of navigation between different UI components (activities, fragments, or composables) in an app",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp))


    }
}
@Composable
fun ShowButton(navController: NavController){
    Button(onClick ={navController.navigate("screen2")},
        modifier = Modifier.padding(top = 128.dp).width(210.dp).height(35.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor =  Color(0xFF2196F3),
            contentColor =  Color.White)
    ) {
        Text(text = "PUSH", fontWeight = FontWeight.Bold)
    }
}

@Composable
fun Screen2(navController: NavController){
    Column(modifier = Modifier.fillMaxSize()){
        LazyColumnScreen2()
        ListText(navController)
    }
}
@Composable
fun LazyColumnScreen2(){
    Row(
        Modifier.fillMaxWidth().padding(top = 28.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            painterResource(id = R.drawable.back),
            contentDescription = "Back",
            modifier = Modifier.padding(start = 16.dp).width(30.dp).height(30.dp)
        )
        Text(text = "Lazy Column",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF2196F3),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 80.dp))
    }
}

@Composable
fun ListText(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    )
    {
        items(5) { index ->
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(62.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(0x4D2196E3)),
                contentAlignment = Alignment.Center

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = " 0${index + 1} | The only way to do great work is to love what you do.",
                        modifier = Modifier.weight(1f)
                            .padding(start = 10.dp,end = 10.dp)
                    )

                    Image(
                        painterResource(id = R.drawable.nextblack),
                        contentDescription = "next",
                        modifier = Modifier.size(37.dp).padding(end = 10.dp)
                            .clickable { navController.navigate("screen3/$index") }
                    )
                }
            }
        }
        item {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(62.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(0x4D2196E3)),
                contentAlignment = Alignment.Center

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "1000000000 | The only way to do great work is to love what you do.",
                        modifier = Modifier.weight(1f)
                            .padding(start = 10.dp, end = 10.dp)
                    )

                    Image(
                        painterResource(id = R.drawable.nextblack),
                        contentDescription = "next",
                        modifier = Modifier.size(37.dp).padding(end = 10.dp)
                            .clickable { navController.navigate("screen3/${999999999}") }
                    )
                }
            }
        }
    }
}

@Composable
fun Screen3(index: Int, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        DetailScreen3(navController)
        Text(
            text = "The only way to do great work is to love what you do",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 50.dp, end = 50.dp, top = 26.dp)
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            TextScreen3(index)
        }
        Button(onClick = {navController.navigate("home")}, modifier = Modifier.fillMaxWidth()
            .height(50.dp)
            .padding(start = 50.dp, end =50.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF3196F3))){
            Text(text = "Back To Root",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)

        }
    }
}
@Composable
fun DetailScreen3(navController: NavController){
    Row(
        Modifier.fillMaxWidth().padding(top = 28.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            painterResource(id = R.drawable.back),
            contentDescription = "Back",
            modifier = Modifier.padding(start = 16.dp).width(30.dp).height(30.dp)
                .clickable { navController.navigate("screen2") }
        )
        Text(text = "Detail",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF2196F3),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 110.dp))
    }
}

@Composable
fun TextScreen3(index: Int) {
    Column(modifier = Modifier
        .padding(31.dp)
        .fillMaxWidth()
        .height(380.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Color(0xFF2196F3))
        ) {
        Text(text = "0${index+1}| The only way to do great work is to love what you do.",
            fontSize = 40.sp,
            lineHeight = 55.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.fillMaxWidth(),
            softWrap = true)
        Text(text="Steve Jobs",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BaiTapT4Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
//            Screen1()
//            Screen2()
//              Screen3()
        }
    }
}