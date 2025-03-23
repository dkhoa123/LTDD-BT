package com.example.baitapt4_2

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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitapt4_2.ui.theme.BaiTapT4_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapT4_2Theme {
                // Khởi tạo viewModel
                val viewModel = TaskViewModel() // Bạn cần tạo class này
                val taskID = 1
                Surface(modifier = Modifier.fillMaxSize(),
                    color = Color.White) {
                    ChangeScreen(viewModel)
                }
            }
        }
    }
}
@Composable
fun ChangeScreen(viewModel: TaskViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){UTHhome(
            navController,
            viewModel = viewModel,
            paddingValues = PaddingValues(0.dp),
            taskID = 1
        )}
        composable("screen2"){ Screen2(navController)}
        composable("screen3/{taskID}"){ backStackEntry ->
            val taskID = backStackEntry.arguments?.getString("taskID")?.toIntOrNull() ?: 0
            Screen3(navController, taskID, viewModel)}
    }
}
@Composable
fun UTHhome(navController: NavController, viewModel: TaskViewModel, paddingValues: PaddingValues, taskID: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        ListScreen()
        TaskListScreen(
            navController,
            viewModel = viewModel,
            paddingValues = PaddingValues(0.dp), // Nếu bạn không muốn padding thêm
            taskID = taskID
        )
    }
}

@Composable
fun TaskListScreen(navController: NavController,viewModel: TaskViewModel, paddingValues: PaddingValues, taskID: Int){
    val selectedTask by viewModel.selectedTask
    LaunchedEffect(Unit) {
        viewModel.fetchTaskById(taskID) // Gọi API khi mở màn hình
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (selectedTask == null) {
            CircularProgressIndicator() // Hiển thị loading nếu chưa có dữ liệu
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(5) { index ->
                    val backGround = listOf(
                        Color(0x4D2196F3),
                        Color(0xFFE1BBC1),
                        Color(0xFFDDE1B6),
                        Color(0xFFE1BBC1),
                        Color(0x4D2196F3)
                    )
                    TaskItem(navController, tasks = selectedTask!!, index, backGround[index]) // Hiển thị thông tin task
                }
            }
        }
    }
}

@Composable
fun TaskItem(navController: NavController, tasks: Tasks, index: Int, backGround: Color) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(5.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(backGround),
        contentAlignment = Alignment.Center

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f).padding(start = 10.dp)) {
                Text(
                    text = tasks.title, fontSize = 18.sp, fontWeight = FontWeight.Bold

                )
                Text(text = tasks.description)
            }
            Image(
                painterResource(id = R.drawable.nextblack),
                contentDescription = "next",
                modifier = Modifier
                    .size(37.dp)
                    .padding(end = 10.dp)
                    .clickable { navController.navigate("screen3/${tasks.id}") })
        }
    }
}
@Composable
fun ListScreen(){
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically){
        Image(painterResource(id = R.drawable.backxanh),
            contentDescription = "back",
            modifier = Modifier
                .padding(start = 10.dp)
                .size(40.dp))
        Text(text = "List",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2196F3),
            modifier = Modifier.weight(1f).padding(end = 50.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Screen2(navController: NavController){
Column(modifier = Modifier.fillMaxSize()) {
    ListScreen2()
    BookSleepScreen2()
    }
}
@Composable
fun ListScreen2(){
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically){
        Image(painterResource(id = R.drawable.backxanh),
            contentDescription = "back",
            modifier = Modifier
                .padding(start = 10.dp)
                .size(40.dp))
        Text(text = "List",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2196F3),
            modifier = Modifier.weight(1f).padding(end = 50.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BookSleepScreen2() {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .height(250.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFFBBBBBB)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.booksleep),
            contentDescription = "booksleep",
            modifier = Modifier.padding(start = 25.dp).size(112.dp)
        )
        Text(
            text = " No Tasks Yet!",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 15.dp)
        )
        Text(
            text = "Stay productive—add something to do",
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Screen3(navController: NavController, taskID: Int, viewModel: TaskViewModel){
    val selectedTask by viewModel.selectedTask

    // Gọi API để lấy task theo ID khi màn hình mở ra
    LaunchedEffect(taskID) {
        println("Fetching task with ID: $taskID")
        viewModel.fetchTaskById(taskID)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        DetailScreen3(navController,viewModel)
        if (selectedTask == null) {
            CircularProgressIndicator() // Đang load dữ liệu
        } else {
            ApiDetail(selectedTask!!)
        }
    }
}
@Composable
fun DetailScreen3(navController: NavController, viewModel: TaskViewModel){
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically){
        Image(painterResource(id = R.drawable.backxanh),
            contentDescription = "back",
            modifier = Modifier
                .padding(start = 10.dp)
                .size(40.dp)
                .clickable {
                    viewModel.clearTask()
                    navController.navigate("home") })
        Text(text = "Detail",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2196F3),
            modifier = Modifier.weight(1f).padding(end = 50.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ApiDetail(tasks: Tasks) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = tasks.title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = tasks.description,
            textAlign = TextAlign.Center
        )

    }
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Subtasks",
            fontWeight = FontWeight.Bold
        )
        if (tasks.subtasks.isNotEmpty()) {
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                tasks.subtasks.forEach { subtasks ->
                    Text(
                        text = subtasks.title,
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 10.dp, start = 10.dp)
                            .height(64.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color(0xFFE6E6E6))
                            .wrapContentHeight(Alignment.CenterVertically)
                    )
                }
            }
        } else {
            // Hiển thị thông báo khi không có subtasks
            Text(
                text = "No subtasks available",
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 10.dp)
                    .height(64.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(0xFFE6E6E6))
            )
        }
        Column(modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            ) {
            Text(
                text = "Attachments",
                fontWeight = FontWeight.Bold
            )
            if (tasks.attachments.isNotEmpty()) {
                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    tasks.attachments.forEach() { attachments ->
                        Text(
                            text = attachments.fileName,
                            modifier = Modifier.fillMaxWidth()
                                .padding(top = 10.dp, start = 10.dp)
                                .height(64.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(Color(0xFFE6E6E6))
                                .wrapContentHeight(Alignment.CenterVertically)

                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BaiTapT4_2Theme {
//        UTHhome(tasks = Tasks)
//        Screen2()
//          Screen3()
    }
}