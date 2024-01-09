package com.example.button_test

import com.google.gson.annotations.SerializedName

data class costResponse(
    @SerializedName("success") var user_success: Boolean,
    @SerializedName("code") var user_code: Int,
    @SerializedName("message")var user_message: String
)
