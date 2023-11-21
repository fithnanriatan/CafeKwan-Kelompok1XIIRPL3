package com.project.cafekwan_kelompok1xiirpl3.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.project.cafekwan_kelompok1xiirpl3.DetailMenu
import com.project.cafekwan_kelompok1xiirpl3.R
import com.project.cafekwan_kelompok1xiirpl3.produk
import com.project.cafekwan_kelompok1xiirpl3.room.TB_MENU
import com.project.cafekwan_kelompok1xiirpl3.room.TB_PESANAN
import com.project.cafekwan_kelompok1xiirpl3.update_menu

class ProdukAdapter(private val list: ArrayList<TB_MENU>,private val listener:onClickListenerProduk) :RecyclerView.Adapter<ProdukAdapter.ViewHolder>()
{
    class ViewHolder(view: View)
        :RecyclerView.ViewHolder(view)
    {
        val kode : TextView = itemView.findViewById(R.id.txt_kode_produk)
        val nama : TextView = itemView.findViewById(R.id.tv_nama_produk)
        val harga : TextView = itemView.findViewById(R.id.tv_harga_produk)
        val status : TextView = itemView.findViewById(R.id.tv_status_produk)
        val detail: CardView=itemView.findViewById(R.id.detailproduk)

        val ubahpsnan : ImageView = itemView.findViewById(R.id.imgEditPsnan)
        val hapuspsnan : ImageView = itemView.findViewById(R.id.imgHpsPsnan)

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_produk,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.kode.text = list[position].kode_menu.toString()
        holder.nama.text = list[position].nama_menu
        holder.harga.text = list[position].harga_menu.toString()
        holder.status.text = list[position].status_menu

        //memperkenalkan variabel & perpindahan
        holder.ubahpsnan.setOnClickListener{
            val context = holder.itemView.context
            context.startActivity(
                Intent(context,update_menu::class.java)
                    .putExtra("kode menu",holder.kode.text)
            )
        }
        holder.hapuspsnan.setOnClickListener(){
            listener.deleteProduk(list[position])
        }
        holder.detail.setOnClickListener{
            val context= holder.itemView.context
            val intent =
            Intent(context, DetailMenu::class.java).putExtra("Idmenu", list[position].kode_menu.toString())
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
    fun setData(newList: List<TB_MENU>){
        list.clear()
        list.addAll(newList)
    }
    interface onClickListenerProduk {
        fun deleteProduk(tbMenu: TB_MENU)
        fun updateProduk(tbMenu: TB_MENU)
    }

}
