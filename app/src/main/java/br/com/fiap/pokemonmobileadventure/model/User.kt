package br.com.fiap.model

data class User(val email:String,
                val nome:String,
                val telefone:String,
                val pokemons: List<Pokemon>){constructor():this("", "", "", emptyList())}