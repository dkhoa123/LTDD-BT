package com.example.baitapt4_2

import com.example.test_api.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface APIServer {
    @GET("tasks")
    suspend fun getTasks(): ApiResponse<List<Tasks>>
    @GET("task/{id}")
    suspend fun getTaskById(@Path("id") id: Int): ApiResponse<Tasks>
}