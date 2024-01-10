package com.example.button_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.button_test.databinding.ActivityEndBinding
import com.example.button_test.databinding.ActivityMainBinding

class EndActivity : AppCompatActivity() {

    val binding by lazy { ActivityEndBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = Intent(this, MainActivity::class.java)
        binding.buttonHome2.setOnClickListener{startActivity(intent)}
    }
}