package br.com.fiap.pokemonmobileadventure.ui.cadastro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import br.com.fiap.model.Pokemon
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.adapter.CapturadosAdapter
import br.com.fiap.pokemonmobileadventure.adapter.PokemonTimeAdapter
import kotlinx.android.synthetic.main.activity_cadastro_time.*

class CadastroTimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_time)




        var adapterTime = PokemonTimeAdapter(this,ArrayList<Pokemon>())
        var adapterCadastro = CapturadosAdapter(this,ArrayList<Pokemon>(),{adapterTime.adicionaPokemon(it) })


        rvTime.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvTime.adapter = adapterTime

        rvCapturados.layoutManager = LinearLayoutManager(this)
        rvCapturados.adapter = adapterCadastro


        btnSalvarTime.setOnClickListener {
            //TODO SALVAR NO BANCO NOME DO TIME E POKEMONS
        }

    }
}
