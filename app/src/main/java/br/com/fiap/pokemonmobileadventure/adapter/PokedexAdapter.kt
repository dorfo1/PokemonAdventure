package br.com.fiap.pokemonmobileadventure.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.model.Pokemon
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.utils.Headers
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
        holder.bindView(position+1)
        holder.nome.text = pokemon.nome
        holder.numero.text = pokemon.id.toString()

        context?.applicationContext?.let {
            Glide.with(it)
                .load(Headers.getUrlWithHeaders("https://pokedexdx.herokuapp.com${pokemon.urlImg}"))
                .into(holder.imagem)
        }

    }

    class PokedexHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nome = itemView.row_pokedex_nome
        val numero = itemView.row_pokedex_numero
        val imagem = itemView.row_pokedex_image

        fun bindView(position: Int) = with(itemView){
            row_pokedex_numero.text = position.toString()
        }


    }

}