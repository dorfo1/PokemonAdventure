package br.com.fiap.pokemonmobileadventure.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import br.com.fiap.model.Pokemon



@Entity
data class Time(
    @ColumnInfo(name = "nome")var nome: String,
    @ColumnInfo(name = "pokemon")var pokemon: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0
    )