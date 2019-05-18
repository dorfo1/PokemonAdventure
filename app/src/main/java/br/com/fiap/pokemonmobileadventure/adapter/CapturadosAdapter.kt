package br.com.fiap.pokemonmobileadventure.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.model.Pokemon
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.utils.Headers
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_pokedex.view.*

class CapturadosAdapter(private var context: Context?,
                        private var pokemons: List<Pokemon>,
                        private val listener: (Pokemon) ->Unit) : RecyclerView.Adapter<CapturadosAdapter.CapturadosHolder>(){



    override fun onCreateViewHolder(container: ViewGroup, p1: Int): CapturadosHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.row_pokedex,container,false)
        return CapturadosHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: CapturadosHolder, position: Int) {
        var pokemon = pokemons.get(position)
        holder.bindView(pokemon,listener)
        Log.d("j","PASSOU")
    }

    fun adicionaLista(pokemons: List<Pokemon>?) {
        this.pokemons = pokemons!!
        notifyDataSetChanged()
    }


    class CapturadosHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        fun bindView(pokemon : Pokemon, listener: (Pokemon) -> Unit) = with(itemView){
            row_pokedex_numero.text = pokemon.id.toString()
            row_pokedex_nome.text = pokemon.nome


            context?.applicationContext?.let {
                Glide.with(it)
                    .load(Headers.getUrlWithHeaders("https://pokedexdx.herokuapp.com${pokemon.urlImg}"))
                    .into(row_pokedex_image)
            }
            if(pokemon.capturado){
                row_pokedex_capturado.visibility = View.VISIBLE
            }else{
                row_pokedex_capturado.visibility = View.INVISIBLE
            }
            itemView.setOnClickListener{
                listener(pokemon)
            }
        }
    }
}