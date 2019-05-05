package br.com.fiap.ui.Main.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.ui.cadastro.CadastroTimeActivity



class TimeFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_time,container,false)


        var FAB = view.findViewById<FloatingActionButton>(R.id.fabNovoTime)

        FAB.setOnClickListener {
            var intent = Intent(activity, CadastroTimeActivity::class.java)
            startActivity(intent)
        }


        return view
    }
}