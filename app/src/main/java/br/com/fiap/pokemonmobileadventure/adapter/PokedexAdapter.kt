package br.com.fiap.pokemonmobileadventure.adapter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.model.Pokemon
import br.com.fiap.pokemonmobileadventure.R
import kotlinx.android.synthetic.main.row_pokedex.view.*
import kotlinx.android.synthetic.main.row_pokedex.view.row_pokedex_nome
import kotlinx.android.synthetic.main.row_pokemon_time.view.*

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
        holder.bindView(position+1)
        if (pokemon != null) {
            holder.nome.text = pokemon.nome
        }

    }


    class PokedexHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nome = itemView.row_pokedex_nome

        fun bindView(position: Int) = with(itemView){
            row_pokedex_numero.text = position.toString()
        }


    }

}