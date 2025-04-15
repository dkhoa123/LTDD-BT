package com.example.btvntuan7.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tasks")
data class Tasks(
    @PrimaryKey(autoGenerate = true) val idtask:Int = 0,
    val title: String,
    val description: String
)

