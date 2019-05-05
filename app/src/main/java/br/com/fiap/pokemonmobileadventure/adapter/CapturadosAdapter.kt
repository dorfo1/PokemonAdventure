package br.com.fiap.pokemonmobileadventure.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.model.Pokemon
import br.com.fiap.pokemonmobileadventure.R

class CapturadosAdapter(private var context: Context
                        ,private var pokemons:List<Pokemon> ) : RecyclerView.Adapter<CapturadosAdapter.CapturadosHolder>(){



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CapturadosHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.row_pokedex,p0,false)
        return CapturadosHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(p0: CapturadosHolder, p1: Int) {

    }


    class CapturadosHolder(itemView:View) : RecyclerView.ViewHolder(itemView)

}