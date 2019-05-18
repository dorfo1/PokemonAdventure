package br.com.fiap.pokemonmobileadventure.ui.capturar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.data.PokemonDatabase
import br.com.fiap.pokemonmobileadventure.data.dao.PokemonDao
import br.com.fiap.pokemonmobileadventure.utils.PokemonUtils
import kotlinx.android.synthetic.main.activity_captura.*
import kotlinx.android.synthetic.main.row_pokedex.view.*

class CapturaActivity : AppCompatActivity() {

    private lateinit var dataBase : PokemonDatabase

    private lateinit var pokemonDao : PokemonDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_captura)

        val pokemonAleatorio = (1..150).random()
        dataBase = PokemonDatabase.getInstance(this)!!
        pokemonDao = dataBase?.PokemonDao()
        var pokemon = pokemonDao?.getPokemonById(pokemonAleatorio)

        PokemonUtils.loadPokemonImage(applicationContext,ivPokemon, pokemon?.urlImg!!)


        btnCapturar.setOnClickListener {
            iniciaCaptura()
        }
    }



    private fun iniciaCaptura() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
