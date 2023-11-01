package com.project.cafekwan_kelompok1xiirpl3.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities =  [TB_MENU::class,TB_PESANAN::class], version = 2)
abstract class DB_CAFE : RoomDatabase(){
    abstract fun dao_cafe():DAO_CAFE

    companion object{
        @Volatile
        private var instance:DB_CAFE? = null

        fun getInstance(context: Context): DB_CAFE{
            if (instance == null){
                instance = Room.databaseBuilder(context, DB_CAFE::class.java,"CAFE KWAN")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}