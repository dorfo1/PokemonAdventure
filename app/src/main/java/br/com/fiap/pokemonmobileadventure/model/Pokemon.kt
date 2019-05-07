package br.com.fiap.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class PokemonResponse(val content:List<Pokemon>)

@Entity
data class Pokemon(
    @ColumnInfo(name = "capturado") var capturado: Boolean,
    @ColumnInfo(name = "nome")  @SerializedName("name") var nome: String,
    @ColumnInfo(name = "urlImagem") @SerializedName("imageURL") var urlImg: String,
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("number") var id: Long = 0
)