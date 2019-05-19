package br.com.fiap.model

import android.os.Parcel
import android.os.Parcelable

data class User(var email:String,
                var nome:String,
                var telefone:String,
                var firebaseId:String,
                var pokemons: List<Pokemon>) : Parcelable

{



    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Pokemon)
    )


    constructor():this("", "", "","", emptyList())



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(nome)
        parcel.writeString(telefone)
        parcel.writeString(firebaseId)
        parcel.writeTypedList(pokemons)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}