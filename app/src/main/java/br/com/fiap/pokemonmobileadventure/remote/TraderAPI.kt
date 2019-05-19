package br.com.fiap.pokemonmobileadventure.remote

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TraderAPI {

    @GET("trocar/{nome}/{firebaseId}")
    fun notificarTroca(@Path("nome") nome:String,@Path("firebaseId") firebaseId:String) : Call<Void>
}
