package com.project.cafekwan_kelompok1xiirpl3

import android.content.Context
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

        // Mengambil username dari Shared Preferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "") 

        binding.txtWelcome.text = username
        
        binding.produk.setOnClickListener {
            val intent = Intent(this, produk::class.java)
            startActivity(intent)
        }

        binding.pesanan.setOnClickListener{
            val intent = Intent(this, pesanan::class.java)
            startActivity(intent)
        }
    }
}
