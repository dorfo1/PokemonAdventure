package br.com.fiap.pokemonmobileadventure.ui.main.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.model.User
import br.com.fiap.pokemonmobileadventure.R
import com.google.firebase.database.*


class TraderFragment : Fragment(){

    private lateinit var mDatabase : FirebaseDatabase
    private lateinit var ref : DatabaseReference
    private val usuarios: MutableList<User> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_trader,container,false)

        var usuarios : MutableList<User> = ArrayList<User>()
        mDatabase = FirebaseDatabase.getInstance()
        ref = mDatabase.reference
        initUsuarioList()

        return view
    }



    private fun initUsuarioList() {
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                usuarios.clear()
                dataSnapshot.children.mapNotNullTo(usuarios) { it.getValue<User>(User::class.java) }
            }

            override fun onCancelled(databaseError: DatabaseError) {
               //erro
            }
        }
        ref.child("Usuario").addListenerForSingleValueEvent(menuListener)
    }
}