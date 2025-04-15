package com.example.btvntuan7.Model

import androidx.room.*

@Database(entities = [Tasks::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun TaskDao(): TaskDao
}
@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Tasks)

    @Query("SELECT * FROM Tasks")
    suspend fun getAll(): List<Tasks>

    @Delete
    suspend fun delete(task: Tasks)
}
