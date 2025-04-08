package com.example.btvn_tuan6.model

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServer {
    interface APIServer {
        @GET("tasks")
        suspend fun getTasks(): ApiResponse<List<Tasks>>
        @GET("task/{id}")
        suspend fun getTaskById(@Path("id") id: Int): ApiResponse<Tasks>
    }
}