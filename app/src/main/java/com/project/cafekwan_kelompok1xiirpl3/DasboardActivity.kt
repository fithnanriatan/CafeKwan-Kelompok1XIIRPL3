package com.project.cafekwan_kelompok1xiirpl3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityDasboardBinding
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityLoginBinding

class DasboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDasboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username= intent.getStringExtra("username").toString()
        binding.txtWelcome.text="$username"

        binding.navbarProduk.setOnClickListener{
            startActivity(Intent(this, produk::class.java))
        }
        binding.navbarPesanan.setOnClickListener{
            startActivity(Intent(this, pesanan::class.java))
        }
    }
}