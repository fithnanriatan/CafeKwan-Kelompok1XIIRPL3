package com.project.cafekwan_kelompok1xiirpl3.room

import androidx.room.*

@Dao
interface DAO_CAFE {
    @Insert
    fun InserData(vararg tbMenu: TB_MENU)
    @Delete
    fun DeleteDataM(vararg tbMenu: TB_MENU)
    @Update
    fun UpdateData(vararg tbMenu: TB_MENU)
    @Query ("SELECT * FROM MENU")
    fun getAllData() : List<TB_MENU>
    @Query ("SELECT * FROM MENU WHERE kode_menu=:id")
    fun getID(id:Int): List<TB_MENU>

    //PESANAN
    @Insert
    fun insertdata (vararg tbPesanan: TB_PESANAN)
    @Delete
    fun DeleteData(vararg tbPesanan: TB_PESANAN)
    @Update
    fun UpdateData(vararg tbPesanan: TB_PESANAN)
    @Query ("SELECT * FROM PESANAN")
    fun getData() : List<TB_PESANAN>
    @Query ("SELECT * FROM PESANAN WHERE kode_pesanan=:id")
    fun getKODE(id: Int): List<TB_PESANAN>

}