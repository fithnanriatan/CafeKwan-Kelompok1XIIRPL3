package com.project.cafekwan_kelompok1xiirpl3

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.project.cafekwan_kelompok1xiirpl3.adapter.ProdukAdapter
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityProdukBinding
import com.project.cafekwan_kelompok1xiirpl3.room.DB_CAFE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class produk : AppCompatActivity() {
    private val db by lazy { DB_CAFE.getInstance(this) }
    private lateinit var database: DB_CAFE
    private lateinit var binding: ActivityProdukBinding
    private lateinit var adapter: ProdukAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil username dari Shared Preferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        
        binding.txtWelcome.text = username

        binding.pesanan.setOnClickListener {
            onBackPressed()
            startActivity(Intent(this, pesanan::class.java))
        }

        binding.dasboard.setOnClickListener {
            onBackPressed()
        }

        adapter = ProdukAdapter(arrayListOf(),
        )
        binding.rcProduk.adapter = adapter
        binding.rcProduk.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        binding.rcProduk.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))
        binding.floatingActionButton2.setOnClickListener {
            startActivity(Intent(this, Insert_menu::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        setData()
    }

    private fun setData() {
        binding.rcProduk.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.dao_cafe().getAllData()
            adapter.setData(data)
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }
        }
        binding.rcProduk.adapter=adapter
    }
}
