package com.project.cafekwan_kelompok1xiirpl3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityPesananBinding

class pesanan : AppCompatActivity() {
    private lateinit var binding: ActivityPesananBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username= intent.getStringExtra("username").toString()
        binding.txtWelcome.text="$username"
    }
}