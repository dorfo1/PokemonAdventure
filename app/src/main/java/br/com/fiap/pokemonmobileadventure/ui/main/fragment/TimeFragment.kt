package br.com.fiap.ui.Main.Fragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.fiap.model.Pokemon
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.adapter.CapturadosAdapter
import br.com.fiap.pokemonmobileadventure.adapter.PokemonTimeAdapter
import br.com.fiap.pokemonmobileadventure.data.PokemonDatabase
import br.com.fiap.pokemonmobileadventure.data.dao.PokemonDao
import java.util.concurrent.Executors


lateinit var pokemonTimeAdapter : PokemonTimeAdapter
    lateinit var capturadosAdapter: CapturadosAdapter
    lateinit var dataBase: PokemonDatabase
    lateinit var pokemonDao: PokemonDao


class TimeFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_time,container,false)

        val tvTimeVazio = view.findViewById<TextView>(R.id.tvTimeVazio)
        val tvCapturadosVazio = view.findViewById<TextView>(R.id.tvCapturadosVazio)

        val rvCapturados = view.findViewById<RecyclerView>(R.id.rvCapturados)
        rvCapturados.layoutManager = LinearLayoutManager(context)
        capturadosAdapter = CapturadosAdapter(context,ArrayList<Pokemon>()) {
            var adicionaPokemon = pokemonTimeAdapter.adicionaPokemon(it)
            adicionaPokemonNoTime(adicionaPokemon,it.id)
            verificaTamanhoDaListaTime(pokemonTimeAdapter.itemCount,tvTimeVazio)
        }
        rvCapturados.adapter = capturadosAdapter

        val rvTime = view.findViewById<RecyclerView>(R.id.rvTime)
        rvTime.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        pokemonTimeAdapter = PokemonTimeAdapter(context, ArrayList<Pokemon>()){
            var removePokemon = pokemonTimeAdapter.removePokemon(it)
            removePokemonDoTime(removePokemon,it.id)
            verificaTamanhoDaListaTime(pokemonTimeAdapter.itemCount,tvTimeVazio)
        }
        rvTime.adapter = pokemonTimeAdapter

         dataBase = PokemonDatabase.getInstance(inflater.context)!!
         pokemonDao = dataBase?.PokemonDao()

        var pokemonsCapturado: LiveData<List<Pokemon>> = pokemonDao!!.getCapturados()
        var pokemonsTime : LiveData<MutableList<Pokemon>> = pokemonDao!!.getTime()


        pokemonsTime.observe(this, Observer {
            pokemonTimeAdapter.adicionaLista(it)
            verificaPokemonsNoTime(it,tvTimeVazio)
        })


        pokemonsCapturado.observe(this, Observer {
            capturadosAdapter.adicionaLista(it)
            verificaPokemonsCapturados(it,tvCapturadosVazio)
        })

        return view
    }

    private fun removePokemonDoTime(removePokemon: Boolean, id: Long) {
        if(removePokemon){
            val executor = Executors.newSingleThreadExecutor()
            executor.execute{
                pokemonDao.removidoDoTime(id)
            }

        }

    }

    private fun adicionaPokemonNoTime(adicionaPokemon: Boolean, id: Long) {
        if(adicionaPokemon){
            val executor = Executors.newSingleThreadExecutor()
            executor.execute{
                pokemonDao.adicionadoNoTime(id)
            }

        }
    }

    private fun verificaTamanhoDaListaTime(itemCount: Int,tvTimeVazio: TextView?) {
        if(itemCount==0){
            tvTimeVazio!!.visibility = View.VISIBLE
        }else{
            tvTimeVazio!!.visibility = View.INVISIBLE
        }
    }

    private fun verificaPokemonsCapturados(pokemons: List<Pokemon>?, tvCapturadosVazio: TextView?) {
        if(pokemons!!.isEmpty()){
            tvCapturadosVazio!!.visibility = View.VISIBLE
        }else{
            tvCapturadosVazio!!.visibility = View.INVISIBLE
        }

    }

    private fun verificaPokemonsNoTime(pokemons: List<Pokemon>?, tvTimeVazio: TextView?) {
        if(pokemons!!.isEmpty()){
            tvTimeVazio!!.visibility = View.VISIBLE
        }else{
            tvTimeVazio!!.visibility = View.INVISIBLE
        }
    }


}