package com.example.test_api

class ApiResponse<T>(
    val isSuccess: Boolean,
    val message: String,
    val data: T
)