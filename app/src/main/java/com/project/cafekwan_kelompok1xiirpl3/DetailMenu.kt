package com.project.cafekwan_kelompok1xiirpl3

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityDetailMenuBinding
import com.project.cafekwan_kelompok1xiirpl3.room.DB_CAFE
import com.project.cafekwan_kelompok1xiirpl3.room.TB_MENU
import com.project.cafekwan_kelompok1xiirpl3.room.TB_PESANAN
import kotlinx.android.synthetic.main.activity_detail_menu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMenu : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMenuBinding
    private val db by lazy { DB_CAFE.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.imageView2.setOnClickListener{
//            editData(TB_MENU(0,"",0,"",0,""))
//        }

        //Hapus Menu
       binding.imageView3.setOnClickListener{
           db.dao_cafe().DeleteDataM(
               TB_MENU(
                   binding.kodemenu.text.toString().toInt(),
                   binding.namamenu.text.toString(),
                   binding.hargamenu.text.toString().toInt(),
                   binding.tvStatusProduk.text.toString(),
                   binding.jumlah.text.toString().toInt(),
                   binding.navbarPesanan.text.toString()
               )
           )
        }

        val id = intent.getStringExtra("Idmenu").toString().toInt()
        val data = db.dao_cafe().getID(id)[0]

        //Tampil Detail
        binding.kodemenu.setText(data.kode_menu.toString())
        binding.namamenu.setText(data.nama_menu)
        binding.hargamenu.setText(data.harga_menu.toString())
        binding.navbarPesanan.setText(data.status_menu)//namaAdmin
        binding.tvStatusProduk.setText(data.deskripsi_menu)


       //Buat Pesanan
        binding.btnBuatPesanan.setOnClickListener {
            db.dao_cafe().insertdata(
                TB_PESANAN(
                    binding.kodemenu.text.toString().toInt(),
                    binding.jumlah.text.toString().toInt(),
                    binding.tvStatusProduk.text.toString(),
                    binding.hargamenu.text.toString().toInt(),
                    binding.navbarPesanan.text.toString(),
                    binding.namamenu.text.toString()
                )
            )
            startActivity(
                Intent(
                    this, pesanan::class.java
                )
            )
            Toast.makeText(applicationContext,"pesanan sedang diproses",
                Toast.LENGTH_SHORT).show()
        }
        binding.backPesanan.setOnClickListener{
            startActivity(Intent(this,produk::class.java))
        }
    }

    //Alert hps menu
    private fun delete (tbMenu: TB_MENU){
        val dialog = AlertDialog.Builder(this)
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

}