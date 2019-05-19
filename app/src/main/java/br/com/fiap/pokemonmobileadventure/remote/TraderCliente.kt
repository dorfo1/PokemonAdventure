package br.com.fiap.pokemonmobileadventure.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TraderCliente<T> {

    fun getTrader(c: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .client(getOkhttpClientAuth().build())
            .baseUrl("http://159.65.71.16:7001/api/treinador/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(c)
    }
}


fun getTraderAPI() : TraderAPI{
    return TraderCliente<TraderAPI>().getTrader(TraderAPI::class.java)
}

