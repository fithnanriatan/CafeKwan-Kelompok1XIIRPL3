package com.project.cafekwan_kelompok1xiirpl3.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "PESANAN")
data class TB_PESANAN(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "kode_pesanan")val kode_pesanan : Int,
    @ColumnInfo (name = "jumlah_menu")val jumlah_menu :Int,
    @ColumnInfo (name = "nama_admin")val nama_admin : String,
    @ColumnInfo (name = "harga_total")val harga_total : Int,
    @ColumnInfo (name = "status_pesanan")val status_pesanan : String,
    @ColumnInfo (name = "nama_produk")val nama_produk : String
)

