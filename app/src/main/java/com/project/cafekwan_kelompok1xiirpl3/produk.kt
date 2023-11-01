package com.project.cafekwan_kelompok1xiirpl3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityProdukBinding

class produk : AppCompatActivity() {
    private lateinit var binding : ActivityProdukBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username= intent.getStringExtra("username").toString()
        binding.txtWelcome.text="$username"

    }
}