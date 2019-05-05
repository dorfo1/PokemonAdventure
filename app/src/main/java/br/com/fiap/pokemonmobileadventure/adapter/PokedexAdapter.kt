package br.com.fiap.pokemonmobileadventure.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.model.Pokemon
import br.com.fiap.pokemonmobileadventure.R
import kotlinx.android.synthetic.main.row_pokedex.view.*

class PokedexAdapter(var context: Context?, var pokemons:MutableList<Pokemon>) : RecyclerView.Adapter<PokedexAdapter.PokedexHolder>(){



    override fun onCreateViewHolder(container: ViewGroup, position: Int): PokedexHolder {
       var view = LayoutInflater.from(context).inflate(R.layout.row_pokedex,container,false)
        return PokedexHolder(view)
    }

    override fun getItemCount(): Int {
        return 150
    }

    override fun onBindViewHolder(holder: PokedexHolder, position: Int) {
//        var pokemon = pokemons.get(position)
        holder.bindView(position+1)
    }


    class PokedexHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(position: Int) = with(itemView){
            row_pokedex_numero.text = position.toString()
        }


    }

}