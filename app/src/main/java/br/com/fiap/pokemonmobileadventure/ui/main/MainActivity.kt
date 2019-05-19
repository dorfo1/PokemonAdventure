package br.com.fiap.pokemonmobileadventure.ui.main

import android.app.Activity
import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.fiap.model.Pokemon
import br.com.fiap.model.PokemonResponse
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.data.PokemonDatabase
import br.com.fiap.pokemonmobileadventure.remote.getPokemonAPI
import br.com.fiap.pokemonmobileadventure.ui.main.fragment.TraderFragment
import br.com.fiap.ui.Main.Fragment.MapaFragment
import br.com.fiap.ui.Main.Fragment.PokedexFragment
import br.com.fiap.ui.Main.Fragment.SobreFragment
import br.com.fiap.ui.Main.Fragment.TimeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {

    val POKEMON_CAPTURADO_REQUEST_CODE : Int = 10
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var ref : DatabaseReference

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        var fragmentManager = supportFragmentManager
        var tx = fragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.navigation_pokedex -> {
                tx.replace(R.id.frame_principal,PokedexFragment())
                tx.commit()
            }
            R.id.navigation_time -> {
                tx.replace(R.id.frame_principal,TimeFragment())
                tx.commit()
            }
            R.id.navigation_mapa -> {
                tx.replace(R.id.frame_principal,MapaFragment())
                tx.commit()
            }
            R.id.navigation_sobre ->{
                tx.replace(R.id.frame_principal,SobreFragment())
                tx.commit()
            }
            R.id.navigation_trader ->{
                tx.replace(R.id.frame_principal,TraderFragment())
                tx.commit()
            }
        }
        true
    }


    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(okHttpClient)
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()
        ref = mDatabase.reference
        val manager = supportFragmentManager
        val tx = manager.beginTransaction()
        tx.replace(R.id.frame_principal, MapaFragment())
        tx.commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_mapa

        
        getPokemonAPI().buscar(150).enqueue(object : Callback<PokemonResponse>{
            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Log.d("TAG","FALHA NA REQUISIÇÃO")
            }

            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                if(response.isSuccessful){
                    var body = response.body()
                    inserirPokemonsNoBanco(body?.content)
                }
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==POKEMON_CAPTURADO_REQUEST_CODE && resultCode== Activity.RESULT_OK){
            val pokemon = data?.getParcelableExtra<Pokemon>("Pokemon")
            val dataBase = PokemonDatabase.getInstance(this)
            val pokemonDao = dataBase?.PokemonDao()
            pokemonDao?.capturado(pokemon?.id!!)
            ref.child("Usuario").child(mAuth.currentUser!!.uid).setValue(pokemon?.nome)
        }
    }

    private fun inserirPokemonsNoBanco(pokemons: List<Pokemon>?) {
        val dataBase = PokemonDatabase.getInstance(this)

        val pokemonDao = dataBase?.PokemonDao()
        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
            pokemons?.forEach {
                pokemonDao?.inserir(it)
            }

            var pokemonsCadastrados =  pokemonDao?.getAll()



            //Simulação de pokemons já capturados
            pokemonDao?.capturado(10)
            pokemonDao?.capturado(100)
            pokemonDao?.capturado(55)
            pokemonDao?.capturado(32)
        }
    }
}
