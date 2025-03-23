package com.example.bttuan4_3

import android.app.FragmentManager.BackStackEntry
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.BackEventCompat
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitapt4_2.TaskViewModel
import com.example.baitapt4_2.Tasks
import com.example.bttuan4_3.ui.theme.BTtuan4_3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BTtuan4_3Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val viewModel = TaskViewModel()
                        ChangScreen(viewModel)
                }
            }
        }
    }
}
@Composable
fun ChangScreen(viewModel: TaskViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){ Screen1(navController, viewModel)}
        composable("screen2"){ Screen2(navController)}
        composable("screen3/{taskId}"){ BackStackEntry ->
            val taskId = BackStackEntry.arguments?.getString("taskId")?.toIntOrNull()?: 0
        Screen3(navController, viewModel, taskId)
        }
    }
}
@Composable
fun Screen1(navController: NavController,viewModel: TaskViewModel) {
    val tasks by viewModel.tasks
    if (tasks.isEmpty()) {
        CircularProgressIndicator()
    } else {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxWidth().zIndex(1f))
                {
                    ImageAndText(navController)
                }
                Image(
                    painterResource(id = R.drawable.galaxy),
                    contentDescription = "galaxy",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .width(255.dp)
                        .height(176.dp)
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(9.dp),
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 123.dp)
                            .weight(1f)
                    )
                    {
                        items(tasks.take(3)) { task ->
                            ListAPI(task, navController)
                        }
                    }
                }
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    TaskBar()
                    // Nút tròn màu xanh nằm trên taskbar
                    Image(
                        painter = painterResource(id = R.drawable.addmauxanh),
                        contentDescription = "add",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.TopCenter)
                            .offset(y = (-40).dp)
                            .zIndex(1f)
                    )
                }
            }

        }
    }

@Composable
fun ImageAndText(navController: NavController) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 36.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = R.drawable.imageuth),
                contentDescription = "ảnh UTH",
                modifier = Modifier
                    .width(68.dp)
                    .height(66.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(0x1A2196F3))
                    .clickable { navController.navigate("screen2") }
            )
            Column(modifier = Modifier.fillMaxWidth().weight(1f).padding(start = 10.dp)) {
                Text(
                    text = "SmartTasks",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2196F3)
                )
                Text(
                    text = "A simple and efficient to-do app",
                    color = Color(0xFF2196F3)
                )
            }
            Image(painterResource(id = R.drawable.chuong),
                contentDescription = "chuông",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(end = 20.dp).size(27.dp)
                    .clickable { navController.navigate("home") })
        }
    }
}

@Composable
fun ListAPI(task: Tasks, navController: NavController){
    var checked by remember {mutableStateOf(false)}
    Column(modifier = Modifier.fillMaxWidth())
    {
            Column(modifier = Modifier.fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .height(142.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE1BBC1)).
                clickable { navController.navigate("screen3/${task.id}") },
                verticalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    )
                    Column(modifier = Modifier.fillMaxWidth())
                    {
                        Text(
                            text = task.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.wrapContentHeight()

                        )
                        Text(
                            text = task.description,
                            fontSize = 18.sp,
                            modifier = Modifier.wrapContentHeight()
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween)
                {
                    Text(text = "Status: ${task.status}",
                        modifier = Modifier.padding(start = 15.dp).wrapContentWidth(),
                        fontWeight = FontWeight.Bold)
                    Text(text = task.dueDate,
                        modifier = Modifier.padding(end = 15.dp).wrapContentWidth())
                }
            }
        }
    }
@Composable
fun TaskBar() {
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .height(59.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFD2D2D2))
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp).weight(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painterResource(id = R.drawable.home_24),
                    contentDescription = "home",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painterResource(id = R.drawable.calendar_month_24),
                    contentDescription = "home",
                    modifier = Modifier.size(30.dp)
                )
                }
                Spacer(modifier = Modifier.width(55.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp).weight(1f),
                    verticalAlignment = Alignment.CenterVertically,

                ) {
                    Image(
                        painterResource(id = R.drawable.baseline_description_24),
                        contentDescription = "home",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painterResource(id = R.drawable.settings_suggest_24),
                        contentDescription = "home",
                        modifier = Modifier.size(30.dp)
                    )
                }
        }
    }
}
@Composable
fun Screen2(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth().zIndex(1f))
        {
            ImageAndText(navController)
        }
        Image(
            painterResource(id = R.drawable.galaxy),
            contentDescription = "galaxy",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .width(255.dp)
                .height(176.dp)
        )
        Column(modifier = Modifier.fillMaxWidth().padding(top = 100.dp))
        {
            BookSleepScreen2()
        }
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
fun Screen3(navController: NavController, viewModel: TaskViewModel, taskID: Int){
    val tasks by viewModel.tasks
    val task = tasks.find { it.id == taskID }
    Column(modifier = Modifier.fillMaxSize()
        ) {
        DetailScreen3(navController,viewModel)
        if(task != null) {
                    ApiDetail(task)

        }
        else{
            Text(
                text = "Task not found",
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                textAlign = TextAlign.Center
            )
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
        Image(painterResource(id=R.drawable.thungrac), contentDescription = "thùng rác",
            modifier = Modifier
                .padding(end = 10.dp)
                .size(40.dp)
                .clickable { navController.navigate("screen2") })
    }
}

@Composable
fun ApiDetail(task: Tasks) {
Column(modifier = Modifier.fillMaxWidth()
    .verticalScroll(rememberScrollState())) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = task.title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = task.description,
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )

    }
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .height(89.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFFE1BBC1)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f).padding(start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = R.drawable.caterory),
                contentDescription = "category",
                modifier = Modifier.size(28.dp)
            )
            Column() {
                Text(
                    text = "Category",
                    fontSize = 14.sp
                )
                Text(
                    text = task.category,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Row(
            modifier = Modifier.weight(1f).padding(start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = R.drawable.status),
                contentDescription = "status",
                modifier = Modifier.size(28.dp)
            )
            Column() {
                Text(
                    text = "Status",
                    fontSize = 14.sp
                )
                Text(
                    text = task.status,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.wrapContentWidth()
                )
            }
        }
        Row(
            modifier = Modifier.weight(1f).padding(start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = R.drawable.priority),
                contentDescription = "priority",
                modifier = Modifier.size(28.dp)
            )
            Column() {
                Text(
                    text = "Priority",
                    fontSize = 14.sp
                )
                Text(
                    text = task.priority,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Subtasks",
            fontWeight = FontWeight.Bold
        )
        if (task.subtasks.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                task.subtasks.forEach() { subtask ->
                    Column(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color(0xFFE6E6E6))
                    ) {
                        Text(
                            text = subtask.title,
                            modifier = Modifier.fillMaxWidth()
                                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                                .height(64.dp)
                                .wrapContentHeight(Alignment.CenterVertically)

                        )
                    }
                }
            }
        }
    }
    if (task.attachments.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        ) {
            Text(
                text = "Attachments",
                fontWeight = FontWeight.Bold
            )
            task.attachments.forEach() { attachment ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color(0xFFE6E6E6))
                    ) {
                        Text(
                            text = attachment.fileName,
                            modifier = Modifier.fillMaxWidth()
                                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                                .height(64.dp)
                                .wrapContentHeight(Alignment.CenterVertically)

                        )
                    }
                }
            }
        }
    }
}
}
@Preview(showBackground = true)
@Composable
fun PreviewScreen2() {
    Screen2(rememberNavController())
}