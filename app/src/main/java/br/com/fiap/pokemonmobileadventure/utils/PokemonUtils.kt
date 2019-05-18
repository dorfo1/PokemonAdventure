package br.com.fiap.pokemonmobileadventure.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_pokedex.view.*

object PokemonUtils {

    fun loadPokemonImage(context : Context,view : ImageView,url : String ) {
        context?.applicationContext?.let {
            Glide.with(it)
                .load(Headers.getUrlWithHeaders("https://pokedexdx.herokuapp.com$url"))
                .into(view)
        }
    }
}