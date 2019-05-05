package br.com.fiap.pokemonmobileadventure.remote

import android.util.Log
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientApi<T> {

    fun getClient(c: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .client(getOkhttpClientAuth().build())
            .baseUrl("https://pokedexdx.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(c)
    }
}

private var picasso : Picasso? = null


fun getOkhttpClientAuth() : OkHttpClient.Builder{
    return OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
}

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val requestBuilder = chain!!.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Basic cG9rZWFwaTpwb2tlbW9u")
        val request = requestBuilder.build()
        val response = chain.proceed(request)
        if (response.code() == 401) {
            Log.e("MEUAPP", "Error API KEY")
        }
        return response
    }

}

fun getPokemonAPI(): PokemonAPI{
    return ClientApi<PokemonAPI>().getClient(PokemonAPI::class.java)
}