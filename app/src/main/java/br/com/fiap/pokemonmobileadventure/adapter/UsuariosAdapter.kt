package br.com.fiap.pokemonmobileadventure.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.model.User
import br.com.fiap.pokemonmobileadventure.R
import kotlinx.android.synthetic.main.row_treinador.view.*


class UsuariosAdapter(private var context: Context,
                      private var usuarios:MutableList<User>,
                      private var listener : (Unit) -> User) : RecyclerView.Adapter<UsuariosAdapter.UsuariosHolder>() {



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): UsuariosHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.row_treinador,p0,false)
        return UsuariosHolder(view)
    }

    override fun getItemCount(): Int {
       return usuarios.size
    }

    override fun onBindViewHolder(holder: UsuariosHolder, position: Int) {
        var usuario = usuarios.get(position)

        holder.bindView(usuario,listener)
    }


    class UsuariosHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(usuario: User, listener: (Unit) -> User) = with(itemView) {
            row_treinador_email.text = usuario.email
            row_treinador_nome.text = usuario.nome
        }
    }
}