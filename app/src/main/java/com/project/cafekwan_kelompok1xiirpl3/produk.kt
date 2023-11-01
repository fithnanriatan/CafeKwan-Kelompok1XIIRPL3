package com.project.cafekwan_kelompok1xiirpl3

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.project.cafekwan_kelompok1xiirpl3.adapter.PesananAdapter
import com.project.cafekwan_kelompok1xiirpl3.room.DB_CAFE
import com.project.cafekwan_kelompok1xiirpl3.room.TB_PESANAN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class produk : AppCompatActivity() {

    private val db by lazy { DB_CAFE.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produk)




        object : PesananAdapter.OnAdapterListener {
            override fun onDelete(tbPesanan: TB_PESANAN) {
                delete(tbPesanan)
            }

            override fun delete(tbPesanan: TB_PESANAN) {
                val dialog = AlertDialog.Builder(this)
                dialog.apply {
                    setTitle("Konfirmasi hapus data")
                    setMessage("apakah anda yakin akan menghapus data ini ${tbPesanan.kode_pesanan}?")
                    setNegativeButton("batal") { dilogInterface: DialogInterface, i: Int ->
                        dilogInterface.dismiss()
                    }
                    setPositiveButton("hapus") { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.dismiss()

                        CoroutineScope(Dispatchers.IO).launch {
                            db.dao_cafe().DeleteData(tbPesanan)
                            finish()
                            startActivity(intent)
                        }
                    }
                    dialog.show()

                }
            }
        }
    }
}