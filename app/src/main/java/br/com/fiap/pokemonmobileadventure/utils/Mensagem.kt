package br.com.fiap.pokemonmobileadventure.utils

import android.content.Context
import android.widget.Toast

class Mensagem {

    companion object {

        fun mensagem(con: Context, msg: String)
        {
            Toast.makeText(con, msg, Toast.LENGTH_LONG).show()
        }

    }

}