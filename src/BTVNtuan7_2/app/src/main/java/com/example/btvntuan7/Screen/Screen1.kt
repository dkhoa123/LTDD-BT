package com.example.btvntuan7.Screen

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.btvntuan7.Model.Tasks
import com.example.btvntuan7.R
import com.example.btvntuan7.ViewModel.TaskViewModel

@Composable
fun Screen1(navController: NavController, taskViewModel: TaskViewModel) {

    val tasks by taskViewModel.taskList.collectAsState()

    LaunchedEffect(Unit) {
        taskViewModel.seedInitialData()
    }
    LaunchedEffect(Unit) {
        taskViewModel.getAllTasks()
    }
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxWidth().zIndex(1f))
            {
                ImageAndText()
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(9.dp),
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 80.dp)
                        .weight(1f)
                )
                {
                    //danh sách
                    items(tasks) { task ->
                        ListAPI(task)
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
                        .clickable { navController.navigate("screen2") }
                )
            }
        }

}

@Composable
fun ImageAndText() {
    Row(modifier = Modifier.fillMaxWidth().padding(start = 10.dp, top = 50.dp),
        verticalAlignment = Alignment.CenterVertically){
        Image(
            painterResource(id = R.drawable.backxanh),
            contentDescription = "back",
            modifier = Modifier
                .padding(start = 10.dp)
                .size(40.dp)
                .clickable {})
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
fun ListAPI(task: Tasks){
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .height(142.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFE1BBC1)).clickable {},
        verticalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.fillMaxWidth()
            .padding(start = 10.dp)
            .clickable {}) {
            Text(
                text = task.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = task.description,
                fontSize = 18.sp
            )
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