package com.example.btvn_tuan6.model

class ApiResponse<T>(
    val isSuccess: Boolean,
    val message: String,
    val data: T
)