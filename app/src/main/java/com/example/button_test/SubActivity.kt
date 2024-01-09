package com.example.button_test

import android.content.Intent
import android.graphics.Color
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.button_test.databinding.ActivitySubBinding
import com.example.button_test.service.cost_service
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

class SubActivity : AppCompatActivity() {
    val binding by lazy{ ActivitySubBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val text_date: TextView = findViewById(R.id.text_date)

        val nowDate = Date()
        val formatType = SimpleDateFormat("yyyy-MM-dd")
        text_date.setText(formatType.format(nowDate))


        val edittext_cost: EditText = findViewById(R.id.editText_cost)
        val button_upload: Button = findViewById(R.id.button_upload)

        edittext_cost.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                //텍스트를 입력 후
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //텍스트 입력 전
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //텍스트 입력 중
                if(edittext_cost.length() > 0) {
                    button_upload.isClickable = true
                    button_upload.setBackgroundColor(Color.rgb(255, 96, 67));
                } else {
                    button_upload.isClickable = false
                    button_upload.setBackgroundColor(Color.rgb(224,224,224));
                }
            }
        })

        binding.buttonUpload.setOnClickListener{
            Log.d("Tag1", "누름")
            val expenseType = "주유비"
            val amount:Double = edittext_cost.getText().toString().toDouble()
            val date = nowDate
            val userId = 1
            startUpload(costRequest(expenseType, amount, date, userId))

        }
        Log.d("Tag1", "성공")
    }

}

private fun startUpload(data: costRequest){
    val info = costInfo.cost_service

    info.task_list_send(data)
        .enqueue(object: retrofit2.Callback<costResponse>{
            override fun onResponse(call: Call<costResponse>,response: Response<costResponse>) {
                //성공
            }

            override fun onFailure(call: Call<costResponse>, t: Throwable) {
                //실패
            }
        })
}

object costInfo{
    val retrofit= Retrofit.Builder()
        .baseUrl("https://c0c3-221-149-4-114.ngrok-free.app/costs")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cost_service: cost_service by lazy{retrofit.create(cost_service::class.java)}
}