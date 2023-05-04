package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query

@Dao
interface TransferenciaDAO {
    @Insert(onConflict = REPLACE)
    fun insert(transferencia: Transferencia)

    @Delete
    fun delete(vararg transferencia: Transferencia)

    @Query("SELECT * FROM transferencia")
    fun loadAllTransfers(): List<Transferencia>

    @Query("DELETE FROM transferencia WHERE id = :id")
    fun deleteTransferenciaById(id: Int)


}