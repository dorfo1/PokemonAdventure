package br.com.fiap.pokemonmobileadventure.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import br.com.fiap.model.Pokemon

@Dao
interface PokemonDao {

    @Query("SELECT * from Pokemon")
    fun getAll(): List<Pokemon>

    @Insert(onConflict = REPLACE)
    fun inserir(pokemon: Pokemon)

    @Query("UPDATE Time SET nome = :timeNome, pokemon = :timePokemon WHERE id = :timeId")
    fun update(timeId: Long, timeNome:String, timePokemon: String)

}