package br.com.fiap.model

import java.io.Serializable

data class User(val email:String,
                val nome:String,
                val telefone:String,
                val pokemons: List<Pokemon>) : Serializable {constructor():this("", "", "", emptyList())}