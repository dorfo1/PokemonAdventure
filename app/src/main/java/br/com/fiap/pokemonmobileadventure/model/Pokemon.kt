package br.com.fiap.model

import com.google.gson.JsonObject

data class Pokemon(  val id: Int,
                     val name: String,
                     val sprites: JsonObject)