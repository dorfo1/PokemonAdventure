package br.com.fiap.pokemonmobileadventure.remote

import br.com.fiap.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonWebService {

    @GET("/api/v2/pokemon/{pokemon}")
    fun getPokemon(@Path("pokemon") userId: String): Call<Pokemon>
}
