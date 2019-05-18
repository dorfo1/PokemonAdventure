package br.com.fiap.pokemonmobileadventure.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.model.User
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.adapter.UsuariosAdapter
import br.com.fiap.pokemonmobileadventure.ui.usuario.UsuarioActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_trader.*


class TraderFragment : Fragment(){

    private lateinit var mDatabase : FirebaseDatabase
    private lateinit var ref : DatabaseReference
    private val usuarios: MutableList<User> = mutableListOf()
    private lateinit var mAuth : FirebaseAuth
    private lateinit var uid : String
    private lateinit var adapter : UsuariosAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_trader,container,false)
        mAuth = FirebaseAuth.getInstance()
        uid = mAuth.currentUser!!.uid



        var rvTreinador = view.findViewById<RecyclerView>(R.id.rvTreinadores)
        rvTreinador.layoutManager = LinearLayoutManager(context)
        adapter = UsuariosAdapter(context,ArrayList<User>()) {
            var intent = Intent(context, UsuarioActivity::class.java)
//            intent.putExtra("Usuario",it as Parcelable)
            startActivity(intent)
//            TODO TRATAR O CLIQUE, PASSANDO O USUARIO PARA OUTRA TELA
        }
        rvTreinador.adapter = adapter
        mDatabase = FirebaseDatabase.getInstance()
        ref = mDatabase.reference
        initUsuarioList()

        return view
    }



    private fun initUsuarioList() {
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                usuarios.clear()
                dataSnapshot.children.forEach {
                    adicionarNaLista(it.key,it.getValue(User::class.java))
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
               //erro
            }
        }
        ref.child("Usuario").addListenerForSingleValueEvent(menuListener)
    }

    private fun adicionarNaLista(key: String?, value: User?) {
        if(!key.equals(uid)){
            adapter.adiconaUsuario(value)
            tvUsuariosVazio?.visibility = View.INVISIBLE
        }
    }


}