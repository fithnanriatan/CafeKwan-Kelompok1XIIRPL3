package com.project.cafekwan_kelompok1xiirpl3

import android.content.Context
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
        // variabel username
        val username = binding.logUsername
        val password = binding.logPasword
        val pw = binding.logPasword.text.split("\\s+".toRegex())
        
        binding.btnLogin.setOnClickListener{
           if (username.text.isNotEmpty() && password.text.isNotEmpty()){
               if (password.text.length >= 8){
                   // menyimpan username ke Shared Preferences
                   val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                   val editor = sharedPreferences.edit()
                   editor.putString("username", username.text.toString())
                   editor.apply()
                   
                   startActivity(Intent(this,DasboardActivity::class.java))
                   alert("selamat datang dicafe kwan ${username.text}")
                   finish()
               }else{
                   alert("password minimal 8 huruf")
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
