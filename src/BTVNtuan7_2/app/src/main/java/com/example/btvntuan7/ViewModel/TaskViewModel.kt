package com.example.btvntuan7.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.btvntuan7.Model.AppDatabase
import com.example.btvntuan7.Model.TaskDao
import com.example.btvntuan7.Model.Tasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {
    val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "Tasks-db"
    ).allowMainThreadQueries()
        .build()
    private val taskDao = db.TaskDao()

    private val _taskList = MutableStateFlow<List<Tasks>>(emptyList())
    val taskList: StateFlow<List<Tasks>> = _taskList

    fun getAllTasks() {
        viewModelScope.launch {
            _taskList.value = taskDao.getAll()
        }
    }

    fun insertTask(task: Tasks) {
        viewModelScope.launch {
            taskDao.insert(task)
            getAllTasks() // refresh list
        }
    }

    private var isFirstTime = true

    fun seedInitialData() {
        if (isFirstTime) {
            isFirstTime = false
            viewModelScope.launch {
                if (taskDao.getAll().isEmpty()) {
                    val sampleTask = Tasks(
                        title = "Welcome Task",
                        description = "Đây là công việc mẫu đầu tiên!"
                    )
                    taskDao.insert(sampleTask)
                    getAllTasks()
                }
            }
        }
    }
}