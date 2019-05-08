package br.com.fiap.pokemonmobileadventure.utils

import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.model.GlideUrl


internal object Headers {

    private val AUTHORIZATION = "Basic cG9rZWFwaTpwb2tlbW9u"

    fun getUrlWithHeaders(url: String): GlideUrl {
        return GlideUrl(
            url, LazyHeaders.Builder()
                .addHeader("Authorization", AUTHORIZATION)
                .build()
        )
    }
}