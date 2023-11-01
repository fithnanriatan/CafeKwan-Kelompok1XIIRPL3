package com.project.cafekwan_kelompok1xiirpl3.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.cafekwan_kelompok1xiirpl3.R
import com.project.cafekwan_kelompok1xiirpl3.pesanan
import com.project.cafekwan_kelompok1xiirpl3.room.TB_PESANAN
import com.project.cafekwan_kelompok1xiirpl3.update_menu

class PesananAdapter(private val list: ArrayList<TB_PESANAN>,
    private val listener:onClickListener):RecyclerView.Adapter<PesananAdapter.ViewHolder>() {

    class ViewHolder(view: View)
        :RecyclerView.ViewHolder(view)
    {
        val kode : TextView = itemView.findViewById(R.id.tv_adapter_pesanan_kode)
        val nama : TextView = itemView.findViewById(R.id.tv_adapter_pesanan_nama)
        val jumlah : TextView = itemView.findViewById(R.id.tv_adapter_pesanan_jumlah)
        val harga : TextView = itemView.findViewById(R.id.tv_adapter_pesanan_harga)
        val pembuat : TextView = itemView.findViewById(R.id.tv_adapter_pesanan_pembuat)
        val status : TextView = itemView.findViewById(R.id.tv_adapter_pesanan_status)

        val ubah : ImageButton = itemView.findViewById(R.id.btn_adp_aksi_edit)
        val hapus : ImageButton = itemView.findViewById(R.id.btn_adp_aksi_drop)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_pesanan,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.kode.text  = list[position].kode_pesanan.toString()
        holder.nama.text = list[position].nama_produk
        holder.jumlah.text = list[position].kode_menu.toString()
        holder.harga.text = list[position].harga_total.toString()
        holder.pembuat.text = list[position].nama_admin
        holder.status.text = list[position].status_pesanan

        holder.hapus.setOnClickListener{
            listener.delete(list[position])
        }
        holder.ubah.setOnClickListener(){
            listener.edit(list[position])
        }




        holder.ubah.setOnClickListener{
            val context = holder.itemView.context // this
            context.startActivity(
                Intent(context, update_menu::class.java)
                    .putExtra("kodePesanan", holder.kode.text)
            )
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }
    fun setData(newList: List<TB_PESANAN>){
        list.clear()
        list.addAll(newList)
    }
    interface onClickListener{
        fun delete(tbPesanan: TB_PESANAN)
        fun edit(tbPesanan: TB_PESANAN)
    }
    }