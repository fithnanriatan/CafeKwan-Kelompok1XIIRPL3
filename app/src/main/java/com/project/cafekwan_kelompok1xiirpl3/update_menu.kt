package com.project.cafekwan_kelompok1xiirpl3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityUpdateMenuBinding
import com.project.cafekwan_kelompok1xiirpl3.room.DB_CAFE
import com.project.cafekwan_kelompok1xiirpl3.room.TB_MENU

class update_menu : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateMenuBinding
    private val db by lazy { DB_CAFE.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backUpdatemenu.setOnClickListener{
            onBackPressed()
        }
        val id = intent.getStringExtra("kode menu").toString().toInt()
        val data = db.dao_cafe().getID(id)[0]

        binding.Edtnamamenu.setText(data.nama_menu)
        binding.Edthargamenu.setText(data.harga_menu.toString())
        binding.Edtstatusmenu.setText(data.status_menu)
        binding.Edtdeskrpsimenu.setText(data.deskripsi_menu)

        binding.btnUbahProduk.setOnClickListener{
            if (binding.Edtnamamenu.text.isNotEmpty()&&
                    binding.Edthargamenu.text.isNotEmpty()&&
                    binding.Edtstatusmenu.text.isNotEmpty()&&
                    binding.Edtdeskrpsimenu.text.isNotEmpty()){

                // update menu
                db.dao_cafe().UpdateDatam(TB_MENU(
                    data.kode_menu,
                    binding.Edtnamamenu.text.toString(),
                    binding.Edthargamenu.text.toString().toInt(),
                    binding.Edtstatusmenu.text.toString(),
                    0,
                    binding.Edtdeskrpsimenu.text.toString()
                ))
                onBackPressed()
                Toast.makeText(applicationContext,"Data berhasil diubah", Toast.LENGTH_SHORT).show()

            } else{
                Toast.makeText(applicationContext, "ubah data terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
