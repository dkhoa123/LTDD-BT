package com.example.btvn_tuan6.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btvn_tuan6.model.Tasks
import com.example.btvn_tuan6.model.RetrofitInstance
import kotlinx.coroutines.launch


class TaskViewModel: ViewModel() {
    private val _tasks = mutableStateOf<List<Tasks>>(emptyList())
    val tasks: State<List<Tasks>> = _tasks

    private val _selectedTask = mutableStateOf<Tasks?>(null)
    val selectedTask: State<Tasks?> = _selectedTask

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            try {
                Log.d("API_CALL", "Starting API call...")
                val response = RetrofitInstance.api.getTasks()
                if (response.isSuccess) {
                    _tasks.value = response.data
                    Log.d("API_CALL", "Successfully loaded ${response.data.size} tasks")
                } else {
                    Log.e("API_ERROR", "API returned error: ${response.message}")
                    // Handle error case
                }
            }
            catch (e: Exception) {
                // Handle Errors
                Log.e("API_ERROR", "Error fetching data: ${e.message}")
                e.printStackTrace()
            }
        }
    }
    fun fetchTaskById(id: Int) {
        viewModelScope.launch {
            try {
                Log.d("API_CALL", "Starting API call for task $id...")
                val response = RetrofitInstance.api.getTaskById(id)
                if (response.isSuccess) {
                    _selectedTask.value = response.data
                    Log.d("API_CALL", "Successfully loaded task ${response.data.id}")
                } else {
                    Log.e("API_ERROR", "API returned error: ${response.message}")
                }
            }
            catch (e: Exception) {
                Log.e("API_ERROR", "Error fetching task: ${e.message}")
                e.printStackTrace()
            }
        }
    }
    fun clearTask() {
        _selectedTask.value = null
    }
}