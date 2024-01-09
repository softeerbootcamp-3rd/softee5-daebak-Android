package com.example.button_test.service

import retrofit2.Call
import com.example.button_test.costRequest
import com.example.button_test.costResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface cost_service {
    // 비용 생성
    @POST("https://c0c3-221-149-4-114.ngrok-free.app/costs")
    fun task_list_send(@Body request: costRequest): Call<costResponse>
}