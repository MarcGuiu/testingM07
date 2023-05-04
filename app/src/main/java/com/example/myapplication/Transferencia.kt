package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Transferencia (
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "concept") var concept: String?,
    @ColumnInfo(name = "importDiners") var importDiners: Int?,
    @ColumnInfo(name = "telefon") var telefon: Int?
) : Serializable