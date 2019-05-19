package br.com.fiap.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class PokemonResponse(val content:List<Pokemon>)

@Entity
data class Pokemon(
    @ColumnInfo(name = "capturado") var capturado: Boolean = false,
    @ColumnInfo(name = "time") var time: Boolean = false,
    @ColumnInfo(name = "nome")  @SerializedName("name") var nome: String,
    @ColumnInfo(name = "urlImagem") @SerializedName("imageURL") var urlImg: String,
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("number") var id: Long = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (capturado) 1 else 0)
        parcel.writeByte(if (time) 1 else 0)
        parcel.writeString(nome)
        parcel.writeString(urlImg)
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }
}