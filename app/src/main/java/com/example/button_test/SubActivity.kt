package com.example.button_test

import android.content.Intent
import android.graphics.Color
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.button_test.databinding.ActivitySubBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

class SubActivity : AppCompatActivity() {
    val binding by lazy{ ActivitySubBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

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

        val text_date: TextView = findViewById(R.id.text_date)

        val utilDate = Date()
        val formatType = SimpleDateFormat("yyyy-MM-dd")
        text_date.setText(formatType.format(utilDate))
    }
}