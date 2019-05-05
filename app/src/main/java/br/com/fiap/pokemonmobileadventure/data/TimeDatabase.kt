package br.com.fiap.pokemonmobileadventure.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.fiap.pokemonmobileadventure.data.dao.TimeDao
import br.com.fiap.pokemonmobileadventure.model.Time

@Database(entities = arrayOf(Time::class), version = 1)
abstract class TimeDatabase : RoomDatabase(){

    abstract fun TimeDao(): TimeDao
    companion object {
        private var INSTANCE: TimeDatabase? = null

        fun getInstance(context: Context): TimeDatabase? {
            if(INSTANCE == null) {
                synchronized(TimeDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        TimeDatabase::class.java, "times.db")
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