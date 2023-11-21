package com.project.cafekwan_kelompok1xiirpl3

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.project.cafekwan_kelompok1xiirpl3.adapter.ProdukAdapter
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityProdukBinding
import com.project.cafekwan_kelompok1xiirpl3.room.DB_CAFE
import com.project.cafekwan_kelompok1xiirpl3.room.TB_MENU
import com.project.cafekwan_kelompok1xiirpl3.room.TB_PESANAN
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
        //menampilkan function dari adapter
        adapter = ProdukAdapter(arrayListOf(),
            object : ProdukAdapter.onClickListenerProduk{
                override fun deleteProduk(tbMenu: TB_MENU) {
                    hps(tbMenu)
                }

                override fun updateProduk(tbMenu: TB_MENU) {
                }

            }
        )
        //menampilkan data
        binding.rcProduk.adapter = adapter
        binding.rcProduk.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        binding.rcProduk.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))
        binding.floatingActionButton2.setOnClickListener {
            startActivity(Intent(this, Insert_menu::class.java))
        }

    }

    private fun hps(tbMenu: TB_MENU) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi hapus data")
            setMessage("apakah anda yakin akan menghapus data ${tbMenu.nama_menu} ini ?")
            setNegativeButton("batal") { dilogInterface: DialogInterface, i: Int ->
                dilogInterface.dismiss()
            }
            setPositiveButton("hapus") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()

                CoroutineScope(Dispatchers.IO).launch {
                    db.dao_cafe().DeleteDataM(tbMenu)
                    finish()
                    startActivity(intent)
                }
            }
            dialog.show()
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
