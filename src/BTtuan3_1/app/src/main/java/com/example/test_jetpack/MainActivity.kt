
package com.example.test_jetpack

import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.lerp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat.Style
import androidx.core.content.contentValuesOf
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.test_jetpack.ui.theme.Test_jetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Test_jetpackTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavThreeScreen();
               }
            }
        }
    }
}
@Composable
fun NavThreeScreen(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home") { HomeScreen(navController) }
        composable("screen2") { Screen2(navController) }
        composable("screen3") { Screen3(navController) }
    }
}
@Composable
fun HomeScreen(navController: NavController){
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
        Text(text = "Jetpack Compose",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
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
        Text(text = "I'm ready", fontWeight = FontWeight.Bold)
    }
}

@Composable
fun Screen2(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextSceen2()
        }
    Column() {
        DisplaySceen2(navController)
        InputSceen2()
        LayoutSceen2()
    }
}
@Composable
fun TextSceen2(){
    Text(text = "UI Components List",
        modifier = Modifier.fillMaxWidth().padding(top = 28.dp),
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF2196F3))}

@Composable
fun DisplaySceen2(navController: NavController){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 63.dp)
        .padding(horizontal = 8.dp)) {
        Text(text = "Display",
            fontWeight = FontWeight.Bold)
        Button(onClick = {navController.navigate("screen3")},
            shape = RoundedCornerShape(7.dp),
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3).copy(alpha = 0.3f)
            )
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Text",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 13.dp),
                    color = Color.Black
                )
                Text(
                    text = "Displays text",
                    modifier = Modifier.padding(horizontal = 13.dp),
                    color = Color.Black
                )
            }
        }
        Button(onClick = {},
            shape = RoundedCornerShape(7.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3).copy(alpha = 0.3f)
            )
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Image",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 13.dp),
                    color = Color.Black
                )
                Text(
                    text = "Displays an image",
                    modifier = Modifier.padding(horizontal = 13.dp),
                    color = Color.Black
                )
            }
        }
    }

}
@Composable
fun InputSceen2(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 21.dp)
        .padding(horizontal = 8.dp)) {
        Text(text = "Input",
            fontWeight = FontWeight.Bold)
        Button(onClick = {},
            shape = RoundedCornerShape(7.dp),
            modifier = Modifier
            .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3).copy(alpha = 0.3f)
            )
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "TextField",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 13.dp),
                    color = Color.Black
                )
                Text(
                    text = "Input field for text",
                    modifier = Modifier.padding(horizontal = 13.dp),
                    color = Color.Black
                )
            }
        }
        Button(onClick = {},
            shape = RoundedCornerShape(7.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3).copy(alpha = 0.3f)
            )
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "TextField",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 13.dp),
                    color = Color.Black
                )
                Text(
                    text = "Input field for text",
                    modifier = Modifier.padding(horizontal = 13.dp),
                    color = Color.Black
                )
            }
        }
    }

}
@Composable
fun LayoutSceen2(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 21.dp)
        .padding(horizontal = 8.dp)) {
        Text(text = "Layout",
            fontWeight = FontWeight.Bold)
        Button(onClick = {},
            shape = RoundedCornerShape(7.dp),
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3).copy(alpha = 0.3f)
            )
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Column",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 13.dp),
                    color = Color.Black
                )
                Text(
                    text = "Arranges elements vertically",
                    modifier = Modifier.padding(horizontal = 13.dp),
                    color = Color.Black
                )
            }
        }
        Button(onClick = {},
            shape = RoundedCornerShape(7.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3).copy(alpha = 0.3f)
            )
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Row",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 13.dp),
                    color = Color.Black
                )
                Text(
                    text = "Arranges elements horizontally",
                    modifier = Modifier.padding(horizontal = 13.dp),
                    color = Color.Black
                )
            }
        }
    }

}

@Composable
fun Screen3(navController: NavController){
Column(
    modifier = Modifier.fillMaxSize()
) {
    DetailScreen3(navController)
    TextDetailScreen3()
   }
}
@Composable
fun DetailScreen3(navController: NavController){
    Row(
        Modifier.fillMaxWidth().padding(top = 28.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon( imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "Back",
            modifier = Modifier.width(30.dp).height(30.dp).clickable { navController.navigate("home")},
            Color(0xFF2196F3)
        )
        Text(text = "Text Detail",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF2196F3),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f))
    }
}
@Composable
fun TextDetailScreen3() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 180.dp, end = 10.dp),
            contentAlignment = Alignment.TopEnd
    ) {
        Image(
            painter = painterResource(id = R.drawable.image2), // Đảm bảo ảnh có trong res/drawable
            contentDescription = "Text Image",
            modifier = Modifier
                .width(354.dp)
                .height(240.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val fakeNavController = rememberNavController()
    Test_jetpackTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    HomeScreen(navController = fakeNavController)
                }
                Box(modifier =  Modifier.fillMaxSize()) {
                    Screen2(navController = fakeNavController);
                }
                Box(modifier =  Modifier.fillMaxSize()) {
                    Screen3(navController = fakeNavController);
                }
            }
        }
    }
}