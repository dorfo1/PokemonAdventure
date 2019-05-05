package br.com.fiap.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Pokemon(
    @ColumnInfo(name = "capturado") var capturado: Boolean,
    @ColumnInfo(name = "nome")var nome: String,
    @ColumnInfo(name = "urlImagem")var urlImg: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0
)