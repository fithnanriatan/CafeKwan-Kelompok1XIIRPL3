package com.project.cafekwan_kelompok1xiirpl3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityInsertMenuBinding
import com.project.cafekwan_kelompok1xiirpl3.room.DB_CAFE
import com.project.cafekwan_kelompok1xiirpl3.room.TB_MENU

class Insert_menu : AppCompatActivity() {
    private lateinit var binding: ActivityInsertMenuBinding
    private lateinit var database: DB_CAFE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = DB_CAFE.getInstance(applicationContext)
        binding.btnBuatProduk.setOnClickListener{
            if(binding.txtkodemenu.text.isNotEmpty()&&
                    binding.txtnamamenu.text.isNotEmpty()&&
                    binding.txthrgamenu.text.isNotEmpty()&&
                    binding.txtsttsmenu.text.isNotEmpty()&&
                    binding.txtdskpsimenu.text.isNotEmpty()
            ){
                database.dao_cafe().InserData(
                    TB_MENU(
                        binding.txtkodemenu.text.toString().toInt(),
                        binding.txtnamamenu.text.toString(),
                        binding.txthrgamenu.text.toString().toInt(),
                        binding.txtsttsmenu.text.toString(),
                        0,
                        binding.txtdskpsimenu.text.toString()
                    )
                )
                binding.txtkodemenu.setText("")
                binding.txtnamamenu.setText("")
                binding.txthrgamenu.setText("")
                binding.txtsttsmenu.setText("")
                binding.txtdskpsimenu.setText("")

                startActivity(
                    Intent(
                        this, MainActivity::class.java
                    )
                )

            }else {
                Toast.makeText(applicationContext,"masukan text dulu lol",
                Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
        }
    }
}