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
import com.project.cafekwan_kelompok1xiirpl3.adapter.PesananAdapter
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityPesananBinding
import com.project.cafekwan_kelompok1xiirpl3.room.DB_CAFE
import com.project.cafekwan_kelompok1xiirpl3.room.TB_PESANAN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class pesanan : AppCompatActivity() {

    private val db by lazy { DB_CAFE.getInstance(this) }
    private lateinit var database: DB_CAFE
    private lateinit var binding: ActivityPesananBinding
    private lateinit var adapter: PesananAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter= PesananAdapter(arrayListOf(),
            object : PesananAdapter.onClickListener{

                override fun delete(tbPesanan: TB_PESANAN) {
                    hpsPsn(tbPesanan)
                }

                override fun edit(tbPesanan: TB_PESANAN) {
                    ubhPsn(tbPesanan)
                }

                override fun insert(tbPesanan: TB_PESANAN) {
                    TODO("Not yet implemented")
                }
            } )

        binding.rcPesanan.adapter=adapter
        binding.rcPesanan.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
        binding.rcPesanan.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))

        // Mengambil username dari Shared Preferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")

        binding.txtWelcome.text = username
        
        binding.produk.setOnClickListener {
            onBackPressed()
            startActivity(Intent(this, produk::class.java))
        }
        binding.dasboard.setOnClickListener {
            onBackPressed()
        }
    }

    private fun hpsPsn(tbPesanan: TB_PESANAN) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi hapus data")
            setMessage("apakah anda yakin akan menghapus data ini ${tbPesanan.nama_produk}?")
            setNegativeButton("batal") { dilogInterface: DialogInterface, i: Int ->
                dilogInterface.dismiss()
            }
            setPositiveButton("hapus") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()

                CoroutineScope(Dispatchers.IO).launch {
                    db.dao_cafe().DeleteData(tbPesanan)
                }
                    recreate()
            }
            dialog.show()
        }
    }

     private fun ubhPsn(tbPesanan: TB_PESANAN) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi pesanan selesai")
            setMessage("apakah pesanan ${tbPesanan.nama_produk}? sudah selesai?")
            setNegativeButton("batal") { dilogInterface: DialogInterface, i: Int ->
                dilogInterface.dismiss()
            }
            setPositiveButton("hapus") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()

                CoroutineScope(Dispatchers.IO).launch {
                    db.dao_cafe().updPsnSelesai(tbPesanan.kode_pesanan)
                }
                    recreate()
            }
            dialog.show()
        }
    }

    override fun onResume() {
        super.onResume()
        getDataPsn()
    }
    fun getDataPsn(){
        binding.rcPesanan.layoutManager=LinearLayoutManager(applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.dao_cafe().getData()
            adapter.setDataPsnan(data)
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
            }
        }
        binding.rcPesanan.adapter = adapter
    }
}
