package br.com.fiap.pokemonmobileadventure.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import br.com.fiap.model.Pokemon

@Dao
interface PokemonDao {

    @Insert(onConflict = REPLACE)
    fun inserir(pokemon: Pokemon)

}