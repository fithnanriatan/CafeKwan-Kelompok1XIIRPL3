package com.project.cafekwan_kelompok1xiirpl3

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import cn.pedant.SweetAlert.SweetAlertDialog
import com.project.cafekwan_kelompok1xiirpl3.adapter.PesananAdapter
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityDetailMenuBinding
import com.project.cafekwan_kelompok1xiirpl3.room.DB_CAFE
import com.project.cafekwan_kelompok1xiirpl3.room.TB_MENU
import com.project.cafekwan_kelompok1xiirpl3.room.TB_PESANAN
import kotlinx.android.synthetic.main.activity_detail_menu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMenu : AppCompatActivity() {

    private lateinit var hrgMenu : TextView
    private lateinit var jmlhPsn : EditText
    private lateinit var kali : Button

    private lateinit var binding: ActivityDetailMenuBinding
    private val db by lazy { DB_CAFE.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hrgMenu = findViewById(R.id.hargamenu)
        jmlhPsn = findViewById(R.id.jumlah)
        kali = findViewById(R.id.btn_buatPesanan)


        val id = intent.getStringExtra("Idmenu").toString().toInt()
        val data = db.dao_cafe().getID(id)[0]

        // Mengambil username dari Shared Preferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")

        //Tampil Detail
        binding.kodemenu.setText(data.kode_menu.toString())
        binding.namamenu.setText(data.nama_menu)
        binding.hargamenu.setText(data.harga_menu.toString())
        binding.statusmenu.setText(data.status_menu)
        binding.descmenu.setText(data.deskripsi_menu) 

        //Buat Pesanan
        binding.btnBuatPesanan.setOnClickListener {
        val jmlMenu = binding.jumlah.text.toString.toInt()
        val harga_total = data.harga_menu * jmlMenu
            db.dao_cafe().insertdata(
                TB_PESANAN(
                    0,
                    jmlMenu,
                    username,
                    harga_total,
                    "diproses",
                    data.nama_menu
                )
            )
            onBackPressed()
            startActivity(Intent(this,pesanan::class.java))
            Toast.makeText(
                applicationContext,
                "pesanan sedang diproses",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.backPesanan.setOnClickListener{
            onBackPressed()
        }
    }

    //Alert hps menu
    private fun delete (tbMenu: TB_MENU){
        val dialog = AlertDialog.Builder(this, SweetAlertDialog.WARNING_TYPE)
        dialog.apply {
            setTitle("Konfirmasi hapus data")
            setMessage("apakah anda yakin akan menghapus data ini?")
            setNegativeButton("batal") { dilogInterface: DialogInterface, i: Int ->
                dilogInterface.dismiss()
            }
            setPositiveButton("hapus") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()

                CoroutineScope(Dispatchers.IO).launch {
                    db.dao_cafe().DeleteDataM(tbMenu)
                    finish()
                    //startActivity(intent)
                }
            }
            dialog.show()
        }
    }

    private fun editData(tbMenu: TB_MENU){
        startActivity(Intent(this,update_menu::class.java)
            .putExtra("kode menu",tbMenu.kode_menu.toString()))
    }

    fun ttlHrg(view: View) {
        val hitung = jmlhPsn.text.toString().toDouble() * hrgMenu.text.toString().toDouble()
        hrgMenu.text = hitung.toString()
    }

}
