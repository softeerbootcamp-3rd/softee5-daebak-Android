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
import com.example.button_test.service.Cost_Service
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date
import java.util.concurrent.TimeUnit

class SubActivity : AppCompatActivity() {
    val binding by lazy{ ActivitySubBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val text_date: TextView = findViewById(R.id.text_date)

        val nowDate = Date()
        val formatType = SimpleDateFormat("yyyy-MM-dd")
        text_date.setText(formatType.format(nowDate))

        var t_expenseType = "주유비"

        binding.buttonOil.setOnClickListener{
            t_expenseType = "주유비"
            binding.imageOil.setImageResource(R.drawable.oil_2)
            binding.imageWash.setImageResource(R.drawable.wash_1)
            binding.imageRepair.setImageResource(R.drawable.repair_1)
        }

        binding.buttonWash.setOnClickListener{
            t_expenseType = "세차비"
            binding.imageOil.setImageResource(R.drawable.oil_1)
            binding.imageWash.setImageResource(R.drawable.wash_2)
            binding.imageRepair.setImageResource(R.drawable.repair_1)
        }

        binding.buttonRepair.setOnClickListener{
            t_expenseType = "수리비"
            binding.imageOil.setImageResource(R.drawable.oil_1)
            binding.imageWash.setImageResource(R.drawable.wash_1)
            binding.imageRepair.setImageResource(R.drawable.repair_2)
        }

        val edittext_cost: EditText = findViewById(R.id.editText_cost)
        val button_upload: Button = findViewById(R.id.button_upload)

        edittext_cost.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                //텍스트 입력 후
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //텍스트 입력 전
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //텍스트 입력 중
                if(edittext_cost.length() > 0) {
                    button_upload.isClickable = true
                    button_upload.setBackgroundColor(Color.rgb(255, 96, 67))
                } else {
                    button_upload.isClickable = false
                    button_upload.setBackgroundColor(Color.rgb(224,224,224))
                }
            }
        })

        binding.buttonUpload.setOnClickListener{
            Log.e("TAG", t_expenseType)
            val t_amount = edittext_cost.getText().toString().toDouble()
            val t_userId = 1
            startUpload(t_expenseType, t_amount, formatType.format(nowDate), t_userId)
        }
    }

}

fun startUpload(expenseType:String, amount:Double, date:String, userId:Int){
    val info = costRequest(expenseType, amount, date, userId)

    costInfo.cost_Service.task_list_send(info)
        .enqueue(object: retrofit2.Callback<costResponse>{
            override fun onResponse(call: Call<costResponse>,response: Response<costResponse>) {
                if (response.isSuccessful()){
                    Log.e("TAG", "성공")
                }
            }

            override fun onFailure(call: Call<costResponse>, t: Throwable) {
                //실패
            }
        })
}