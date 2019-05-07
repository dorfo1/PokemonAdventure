package br.com.fiap.pokemonmobileadventure.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import br.com.fiap.model.Pokemon

@Dao
interface PokemonDao {

    @Query("SELECT * from Pokemon")
    fun getAll(): List<Pokemon>

    @Insert(onConflict = IGNORE )
    fun inserir(pokemon: Pokemon)

    @Query("UPDATE Pokemon SET nome = :nome, urlImagem = :img, capturado = :capturado WHERE id = :timeId")
    fun update(timeId: Long, nome:String, img: String, capturado: Boolean)

}