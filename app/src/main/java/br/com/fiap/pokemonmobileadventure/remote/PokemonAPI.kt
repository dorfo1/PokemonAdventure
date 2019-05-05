package br.com.fiap.pokemonmobileadventure.remote

import br.com.fiap.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonAPI {

    @GET("/api/pokemon/")
    fun buscar(@Query("size")size : Int): Call<PokemonResponse>

}