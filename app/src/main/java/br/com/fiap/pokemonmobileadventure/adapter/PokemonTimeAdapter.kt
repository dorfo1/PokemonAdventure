package br.com.fiap.pokemonmobileadventure.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.model.Pokemon
import br.com.fiap.pokemonmobileadventure.R
import android.view.WindowManager
import br.com.fiap.pokemonmobileadventure.utils.Headers
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_pokemon_time.view.*


class PokemonTimeAdapter(private val context: Context?,
                         private var pokemons:MutableList<Pokemon>,
                         private val listener: (Pokemon) ->Unit) : RecyclerView.Adapter<PokemonTimeAdapter.PokemonTimeHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PokemonTimeHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.row_pokemon_time,null,false)
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
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
        holder.bindView(pokemon,listener)
    }

    fun adicionaPokemon(pokemon: Pokemon) : Boolean {
        if(podeInserirEssePokemon(pokemon)){
            pokemons.add(pokemon)
            notifyDataSetChanged()
            return true
        }else{
            return false
        }
    }

    private fun podeInserirEssePokemon(pokemon: Pokemon): Boolean {
        var podeInserir = true
        if(itemCount==6){
            return false
        }
        pokemons.forEach {
            if(pokemon.id==it.id){
                podeInserir = false
            }
        }
        return podeInserir
    }

    fun removePokemon(pokemon: Pokemon) : Boolean {
        pokemons.remove(pokemon)
        notifyDataSetChanged()
        return true
    }

    fun adicionaLista(pokemons: MutableList<Pokemon>?){
        this.pokemons = pokemons!!
        notifyDataSetChanged()
    }


    class PokemonTimeHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        fun bindView(pokemon : Pokemon, listener: (pokemon:Pokemon) -> Unit) = with(itemView){
            row_pokedex_nome.text = pokemon.nome
            context?.applicationContext?.let {
                Glide.with(it)
                    .load(Headers.getUrlWithHeaders("https://pokedexdx.herokuapp.com${pokemon.urlImg}"))
                    .into(row_pokedex_image)
            }
            itemView.setOnClickListener{
                listener(pokemon)
            }
        }
    }

}