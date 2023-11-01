package com.project.cafekwan_kelompok1xiirpl3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.cafekwan_kelompok1xiirpl3.R
import com.project.cafekwan_kelompok1xiirpl3.produk
import com.project.cafekwan_kelompok1xiirpl3.room.TB_MENU

class ProdukAdapter(private val list: ArrayList<TB_MENU>)
    :RecyclerView.Adapter<ProdukAdapter.ViewHolder>()
{
    class ViewHolder(view: View)
        :RecyclerView.ViewHolder(view)
    {
        val kode : TextView = itemView.findViewById(R.id.tv_kode_produk)
        val nama : TextView = itemView.findViewById(R.id.tv_nama_produk)
        val harga : TextView = itemView.findViewById(R.id.tv_harga_produk)
        val status : TextView = itemView.findViewById(R.id.tv_status_produk)
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
        holder.kode.text = list[position].toString()
        holder.nama.text = list[position].toString()
        holder.harga.text = list[position].toString()
        holder.status.text = list[position].toString()
    }
    override fun getItemCount(): Int {
        return list.size
    }
    fun setDataProduk(newList: List<TB_MENU>){
        list.clear()
        list.addAll(newList)
    }

}