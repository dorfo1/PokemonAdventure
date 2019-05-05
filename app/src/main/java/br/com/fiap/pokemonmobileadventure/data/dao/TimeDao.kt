package br.com.fiap.pokemonmobileadventure.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import br.com.fiap.model.Pokemon
import br.com.fiap.pokemonmobileadventure.model.Time

@Dao
    interface TimeDao{

    @Query("SELECT * from Time")
    fun getAll(): List<Time>

    @Insert(onConflict = REPLACE)
    fun inserir(time: Time)

    @Delete
    fun deletar(time: Time)

    //@Query("UPDATE Time SET nome = :timeNome, pokemon = :timePokemon WHERE id = :timeId")
    //fun update(timeId: Long, timeNome:String, timePokemon: String)

}