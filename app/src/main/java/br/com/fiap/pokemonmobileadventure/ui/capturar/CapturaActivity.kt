package br.com.fiap.pokemonmobileadventure.ui.capturar

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import br.com.fiap.model.Pokemon
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.data.PokemonDatabase
import br.com.fiap.pokemonmobileadventure.data.dao.PokemonDao
import br.com.fiap.pokemonmobileadventure.utils.PokemonUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_captura.*
import kotlinx.android.synthetic.main.layout_captura.*
import kotlinx.android.synthetic.main.row_pokedex.view.*
import java.util.concurrent.Executors

class CapturaActivity : AppCompatActivity() {

    private lateinit var dataBase : PokemonDatabase
    private lateinit var pokemon : Pokemon
    private lateinit var pokemonDao : PokemonDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_captura)
        supportActionBar?.hide()
        val pokemonAleatorio = (1..150).random()
        val executor = Executors.newSingleThreadExecutor()

        dataBase = PokemonDatabase.getInstance(this)!!
        pokemonDao = dataBase?.PokemonDao()

        executor.execute {
             pokemon = pokemonDao?.getPokemonById(pokemonAleatorio)
             populaUI(pokemon)
        }


        btnCapturar.setOnClickListener {
            iniciaCaptura(pokemon)
        }

        btnCapturaConfirmar.setOnClickListener {
            var intent = Intent()
            intent.putExtra("Pokemon",pokemon)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

    private fun populaUI(pokemon: Pokemon) {
        //PokemonUtils.loadPokemonImage(applicationContext,ivPokemon, pokemon?.urlImg!!)
        tvPokemon.text = "A wild "+pokemon?.nome + " appears"
        //Glide.with(applicationContext).load("https://pokedexdx.herokuapp.com${pokemon.urlImg}").into(ivPokemon)
        if(pokemon.capturado) ivCapturado.visibility = View.VISIBLE
    }

    @SuppressLint("RestrictedApi")
    private fun iniciaCaptura(pokemon: Pokemon) {
        contraintCaptura.visibility = View.VISIBLE
        PokemonUtils.loadPokemonImage(applicationContext,ivCapturaPokemon, pokemon?.urlImg!!)
        val captura = (1..10).random()
        if(captura>5){
            tvCapturaMsg.text = pokemon.nome + " fugiu!"
            fabCompartlhar.visibility = View.INVISIBLE
            btnCapturaConfirmar.visibility = View.INVISIBLE
            finalizaAcitivty()
        }else{
            tvCapturaMsg.text = pokemon.nome + " capturado!"
        }
    }

    private fun finalizaAcitivty() {
        var runnable= object : Runnable {
            override fun run() {
                finish()
            }
        }
        Handler().postDelayed(runnable,2000)
    }
}
