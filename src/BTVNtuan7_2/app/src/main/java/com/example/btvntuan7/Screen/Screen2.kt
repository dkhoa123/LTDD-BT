package com.example.btvntuan7.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.btvntuan7.Model.Tasks
import com.example.btvntuan7.R
import com.example.btvntuan7.ViewModel.TaskViewModel

@Composable
fun Screen2(navController: NavController, taskViewModel: TaskViewModel) {

    Column(modifier = Modifier.fillMaxSize())
    {
        ImageAndAddNew(navController)
        TextAndFieldText(taskViewModel)
    }


}

@Composable
fun ImageAndAddNew(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
        verticalAlignment = Alignment.CenterVertically){
        Image(
            painterResource(id = R.drawable.backxanh),
            contentDescription = "back",
            modifier = Modifier
                .padding(start = 10.dp)
                .size(40.dp)
                .clickable {
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
fun TextAndFieldText(taskViewModel: TaskViewModel){
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Column {
            Text(
                text = "Task",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(value = title,
                onValueChange = { title = it },
                placeholder = { Text("Enter task title") })
            Text(
                text = "Description",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp)
            )
            OutlinedTextField(value = description,
                onValueChange = { description = it },
                placeholder = { Text("Enter task description") }
            )
        }
        Button(onClick = {

            if (title.isNotEmpty() && description.isNotEmpty()) {
                taskViewModel.insertTask(Tasks(title = title, description = description))
                title = ""
                description = ""
            }
        },
            colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
        ) {
            Text(text = "Add")
        }
    }
}