package br.com.fiap.ui.Main.Fragment

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

class PokedexFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_pokedex,container,false)
        var rvPokedex = view.findViewById<RecyclerView>(R.id.rvPokedex)
        rvPokedex.layoutManager = LinearLayoutManager(context)
        rvPokedex.adapter = PokedexAdapter(context,ArrayList<Pokemon>())
        return view
    }
}