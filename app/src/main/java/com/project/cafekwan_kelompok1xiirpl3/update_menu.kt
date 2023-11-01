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

        val id = intent.getStringExtra("idpesanan").toString().toInt()
        val data = db.dao_cafe().getID(id)

        binding.txtnamamenu.setText(data[0].nama_menu)
        binding.txthargamenu.setText(data[0].harga_menu.toString())
        binding.txtstatusmenu.setText(data[0].status_menu)
        binding.txtdeskrpsimenu.setText(data[0].deskripsi_menu)

        binding.btnUbahProduk.setOnClickListener{
            if (binding.txtnamamenu.text.isNotEmpty()&&
                    binding.txthargamenu.text.isNotEmpty()&&
                    binding.txtstatusmenu.text.isNotEmpty()&&
                    binding.txtdeskrpsimenu.text.isNotEmpty()){

                db.dao_cafe().UpdateData(TB_MENU(
                    0,
                    binding.txtnamamenu.text.toString(),
                    binding.txthargamenu.text.toString().toInt(),
                    binding.txtstatusmenu.text.toString(),
                    0,
                    binding.txtdeskrpsimenu.text.toString()
                ))
                Toast.makeText(applicationContext,"Data berhasil diubah", Toast.LENGTH_SHORT).show()
                onBackPressed()
                startActivity(
                    Intent(this,MainActivity::class.java))
            } else{
                Toast.makeText(applicationContext, "ubah data terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }
}