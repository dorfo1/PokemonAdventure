package br.com.fiap.pokemonmobileadventure.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.model.Pokemon
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.utils.Headers
import br.com.fiap.pokemonmobileadventure.utils.PokemonUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_pokedex.view.*

class PokedexAdapter(var context: Context?, val pokemons: List<Pokemon>) : RecyclerView.Adapter<PokedexAdapter.PokedexHolder>(){


    override fun onCreateViewHolder(container: ViewGroup, position: Int): PokedexHolder {
       var view = LayoutInflater.from(context).inflate(R.layout.row_pokedex,container,false)
        return PokedexHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size!!
    }

    override fun onBindViewHolder(holder: PokedexHolder, position: Int) {
        var pokemon = pokemons.get(position)
        holder.bindView(pokemon)
    }

    class PokedexHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(pokemon:Pokemon) = with(itemView){
            row_pokedex_numero.text = pokemon.id.toString()
            row_pokedex_nome.text = pokemon.nome


            PokemonUtils.loadPokemonImage(context,row_pokedex_image,pokemon.urlImg)
            if(pokemon.capturado){
                row_pokedex_capturado.visibility = View.VISIBLE
            }else{
                row_pokedex_capturado.visibility = View.INVISIBLE
            }
        }


    }

}