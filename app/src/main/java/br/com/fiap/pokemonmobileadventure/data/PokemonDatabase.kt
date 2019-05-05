package br.com.fiap.pokemonmobileadventure.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.fiap.model.Pokemon
import br.com.fiap.pokemonmobileadventure.data.dao.PokemonDao

@Database(entities = arrayOf(Pokemon::class), version = 1)
abstract class PokemonDatabase : RoomDatabase(){

    abstract fun PokemonDao(): PokemonDao
    companion object {
        private var INSTANCE: PokemonDatabase? = null

        fun getInstance(context: Context): PokemonDatabase? {
            if (INSTANCE == null){
                synchronized(PokemonDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PokemonDatabase::class.java, "pokemon.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}