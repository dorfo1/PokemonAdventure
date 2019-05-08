package br.com.fiap.ui.Main.Fragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.model.Pokemon
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.adapter.PokedexAdapter
import br.com.fiap.pokemonmobileadventure.data.PokemonDatabase
import java.util.concurrent.Executors

class PokedexFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_pokedex,container,false)
        var rvPokedex = view.findViewById<RecyclerView>(R.id.rvPokedex)
        rvPokedex.layoutManager = LinearLayoutManager(context)


        val dataBase = PokemonDatabase.getInstance(inflater.context)

        val pokemonDao = dataBase?.PokemonDao()
        val executor = Executors.newSingleThreadExecutor()

        var liveDataPokemon: LiveData<List<Pokemon>> = pokemonDao!!.getCapturados()

        liveDataPokemon.observe(this, Observer {
            rvPokedex.adapter = it?.let { it1 -> PokedexAdapter(context, it1) }
        })

        return view
    }
}