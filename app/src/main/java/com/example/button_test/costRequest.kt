package com.example.button_test

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.Date

data class costRequest(
    @SerializedName("expenseType") var user_expenseType: String,
    @SerializedName("amount") var user_amount: Double,
    @SerializedName("date") var user_date: Date,
    @SerializedName("userId") var user_userId: Int
)

