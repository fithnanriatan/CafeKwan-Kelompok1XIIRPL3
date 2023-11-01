package com.project.cafekwan_kelompok1xiirpl3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityDetailMenuBinding
import com.project.cafekwan_kelompok1xiirpl3.room.DB_CAFE

class DetailMenu : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMenuBinding
    private val db by lazy { DB_CAFE.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var id = intent.getStringExtra("Idmenu").toString().toInt()
        val data = db.dao_cafe().getID(id)[0]

        binding.kodemenu.setText(data.kode_menu)
        binding.namamenu.setText(data.nama_menu)
        binding.hargamenu.setText(data.harga_menu)
        binding.navbarPesanan.setText(data.status_menu)
        binding.tvStatusProduk.setText(data.deskripsi_menu)

    }
}