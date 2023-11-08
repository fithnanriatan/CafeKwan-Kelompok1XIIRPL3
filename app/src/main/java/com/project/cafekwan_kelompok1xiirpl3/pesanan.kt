package com.project.cafekwan_kelompok1xiirpl3

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.project.cafekwan_kelompok1xiirpl3.adapter.PesananAdapter
import com.project.cafekwan_kelompok1xiirpl3.adapter.ProdukAdapter
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityPesananBinding
import com.project.cafekwan_kelompok1xiirpl3.room.DB_CAFE
import com.project.cafekwan_kelompok1xiirpl3.room.TB_PESANAN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class pesanan : AppCompatActivity() {

    private val db by lazy { DB_CAFE.getInstance(this) }
    private lateinit var database: DB_CAFE
    private lateinit var binding: ActivityPesananBinding
    private lateinit var adapter: PesananAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username")

        binding.txtWelcome.text = username
        
        binding.produk.setOnClickListener {
            startActivity(Intent(this, produk::class.java)
               .putExtra("username",username)
           )
        }
        binding.dasboard.setOnClickListener {
            val intent = Intent()
            intent.putExtra("username", username)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

}
