package com.example.button_test.service

import retrofit2.Call
import com.example.button_test.costRequest
import com.example.button_test.costResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Cost_Service {
    // 비용 생성
    @POST("costs")
    fun task_list_send(@Body request: costRequest): Call<costResponse>
}