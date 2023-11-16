package com.project.cafekwan_kelompok1xiirpl3

import android.R
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityInsertMenuBinding
import com.project.cafekwan_kelompok1xiirpl3.room.DB_CAFE
import com.project.cafekwan_kelompok1xiirpl3.room.TB_MENU

class Insert_menu : AppCompatActivity() {
    private lateinit var binding: ActivityInsertMenuBinding
    private lateinit var database: DB_CAFE
    private lateinit var selectedItem : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data1 = arrayOf("Pilih Status", "Tersedia", "Kosong")
        val spinner = binding.txtsttsmenu
        val spinnerAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, data1)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
        val textView = spinner.getChildAt(0) as? TextView
        textView?.setTextColor(Color.RED)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        //spinner.setSelection(.toInt())

        database = DB_CAFE.getInstance(applicationContext)

        binding.btnBuatProduk.setOnClickListener{
            if(binding.txtkodemenu.text.isNotEmpty()&&
                    binding.txtnamamenu.text.isNotEmpty()&&
                    binding.txthrgamenu.text.isNotEmpty()&&
                    selectedItem !=="Pilih Status" &&
                    binding.txtdskpsimenu.text.isNotEmpty()
            ){
                database.dao_cafe().InserData(
                    TB_MENU(
                        binding.txtkodemenu.text.toString().toInt(),
                        binding.txtnamamenu.text.toString(),
                        binding.txthrgamenu.text.toString().toInt(),
                        selectedItem,
                        0,
                        binding.txtdskpsimenu.text.toString()
                    )
                )

                onBackPressed()

            }else {
                Toast.makeText(applicationContext,"masukan text dulu lol",
                Toast.LENGTH_SHORT).show()
            }
        }
        binding.imgKembali.setOnClickListener{
            onBackPressed()
        }
    }
}
