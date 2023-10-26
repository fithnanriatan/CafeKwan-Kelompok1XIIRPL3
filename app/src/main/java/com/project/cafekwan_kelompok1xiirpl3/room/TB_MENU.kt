package com.project.cafekwan_kelompok1xiirpl3.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "MENU")
data class TB_MENU(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "kode_menu")val kode_menu : Int,
    @ColumnInfo (name = "nama_menu")val nama_menu : String,
    @ColumnInfo (name = "harga_menu")val harga_menu : Int,
    @ColumnInfo (name = "status_menu")val status_menu : String,
    @ColumnInfo (name = "terjual_menu")val terjual_menu : Int,
)
