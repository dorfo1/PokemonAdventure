package br.com.fiap.pokemonmobileadventure.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.model.Pokemon
import br.com.fiap.pokemonmobileadventure.R
import android.view.WindowManager



class PokemonTimeAdapter(private val context:Context,
                         private var pokemons:MutableList<Pokemon>) : RecyclerView.Adapter<PokemonTimeAdapter.PokemonTimeHolder>() {



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PokemonTimeHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.row_pokemon_time,null,false)
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val width = windowManager.defaultDisplay.width / 5
        val lp = RecyclerView.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams= lp
        return PokemonTimeHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: PokemonTimeHolder, position: Int) {
        var pokemon = pokemons.get(position)
        holder.bindView(pokemon,listener = {
            pokemons.removeAt(it)
            notifyDataSetChanged()
        })
    }

    fun adicionaPokemon(pokemon: Pokemon) {
        pokemons.add(pokemon)
        notifyDataSetChanged()
    }


    class PokemonTimeHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        fun bindView(pokemon : Pokemon, listener: (posicao:Int) -> Unit) = with(itemView){
            itemView.setOnClickListener{
                listener(adapterPosition)
            }
        }
    }

}