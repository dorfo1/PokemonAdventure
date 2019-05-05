package br.com.fiap.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.JsonObject

@Entity
data class Time(
    @ColumnInfo(name = "nome")var nome: String,
    @ColumnInfo(name = "pokemon")var pokemon: JsonObject,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0
)