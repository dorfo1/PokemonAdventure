package br.com.fiap.ui.Main.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.ui.cadastro.CadastroTimeActivity
import kotlinx.android.synthetic.main.fragment_time.*


class TimeFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_time,container,false)
        /*val recyclerView = view.findViewById<RecyclerView>(R.id.rvTime)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        //botão de cadastro
        view.btnSalvarTime.setOnClickListener {
            val intent = Intent(activity, CadastroTimeActivity::class.java)
            startActivity(intent)
        }*/


        return view
    }
}