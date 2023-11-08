package com.project.cafekwan_kelompok1xiirpl3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.project.cafekwan_kelompok1xiirpl3.databinding.ActivityLoginBinding
import com.project.cafekwan_kelompok1xiirpl3.room.DB_CAFE

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: DB_CAFE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = DB_CAFE.getInstance(applicationContext)
        val username =binding.logUsername
        val password =binding.logPasword
        val pw = binding.logPasword.text.split("\\s+".toRegex())
        binding.btnLogin.setOnClickListener{
           if (username.text.isNotEmpty() && password.text.isNotEmpty()){
               if (password.text.length >=9){
                   startActivity(Intent(this, DasboardActivity::class.java)
                       .putExtra("username",username.text.toString())
                   )
                   alert("selamat datang dicafe kwan ${username.text}")
                   finish()
               }else{
                   alert("password minimal 9 huruf")
               }
           }else{
               alert("username dan password tidak boleh kosong!")
           }
        }
    }

    private fun alert(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT )
            .show()
    }
}
