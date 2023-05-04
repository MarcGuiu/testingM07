package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Transferencia::class],
    version = 2
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun TransferenciaDAO(): TransferenciaDAO

    //Part de codi que s'encarrega de la creació, comprobació o esborrar una BD al executar o elimimnar una app
    companion object {
        private var INSTANCE: AppDatabase? = null

        //comproba si la variable "INSTANCE" es null (si existeix o no la BD). Si INSTANCE != null, torna la BD (variable INSTANCE
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "cashtransfere.db").build()
                } }
            return INSTANCE
        }

        //Elimina la instancia de la BD(pesa molt)
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}