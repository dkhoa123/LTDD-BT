package com.example.btvn_tuan6.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.btvn_tuan6.R
import com.example.btvn_tuan6.model.Tasks
import com.example.btvn_tuan6.ui.theme.BTVN_tuan6Theme
import com.example.btvn_tuan6.viewmodel.TaskViewModel

@Composable
fun Screen2(navController: NavController, viewModel: TaskViewModel ,taskId:Int) {
    val tasks by viewModel.tasks
    val task = tasks.find { it.id == taskId }

        Column(modifier = Modifier.fillMaxSize())
        {
            ImageAndAddNew(navController,viewModel)
            if(task != null) {
                TextAndFieldText(task)
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
fun ImageAndAddNew(navController: NavController,viewModel: TaskViewModel) {
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically){
        Image(
            painterResource(id = R.drawable.backxanh),
            contentDescription = "back",
            modifier = Modifier
                .padding(start = 10.dp)
                .size(40.dp)
                .clickable {
                    viewModel.clearTask()
                    navController.navigate("home")
                })
        Text(text = "Add New",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2196F3),
            modifier = Modifier.weight(1f).padding(end = 50.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TextAndFieldText(task:Tasks){
    Column(modifier = Modifier.fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Column {
            Text(
                text = "Task",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(value = task.title,
                onValueChange = {},
                readOnly = true)
            Text(
                text = "Description",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp)
            )
            OutlinedTextField(value = task.description,
                onValueChange = {},
                readOnly = true)
        }
        Button(onClick = {},
            colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
            ) {
            Text(text = "Add")
        }
    }
}
