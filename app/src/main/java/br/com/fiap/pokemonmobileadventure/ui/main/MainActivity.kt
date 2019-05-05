package br.com.fiap.pokemonmobileadventure.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.fiap.model.Pokemon

import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.remote.PokemonWebService
import br.com.fiap.ui.Main.Fragment.MapaFragment
import br.com.fiap.ui.Main.Fragment.PokedexFragment
import br.com.fiap.ui.Main.Fragment.SobreFragment
import br.com.fiap.ui.Main.Fragment.TimeFragment
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.android.gms.maps.SupportMapFragment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

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

        val manager = supportFragmentManager
        val tx = manager.beginTransaction()
        tx.replace(R.id.frame_principal, MapaFragment())
        tx.commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_mapa

        var retrofit = provideRetrofit(GsonBuilder().create(), OkHttpClient.Builder()
            .addNetworkInterceptor( StethoInterceptor())
            .build())

        var teste = retrofit.create(PokemonWebService::class.java)

        teste.getPokemon("10").enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                Log.i("TESTE", response.body().toString() )
            }
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                println(t.message)
            }
        })




    }
}
