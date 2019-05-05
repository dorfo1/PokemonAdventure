package br.com.fiap.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.JsonObject

@Entity
data class Pokemon(
    @ColumnInfo(name = "nome")var nome: String,
    @ColumnInfo(name = "urlImage")var urlImage: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0
)