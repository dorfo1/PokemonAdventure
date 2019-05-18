package br.com.fiap.pokemonmobileadventure.ui.capturar

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.fiap.pokemonmobileadventure.R
import kotlinx.android.synthetic.main.dialog_capturar.*


class CapturarDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_capturar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCapturar.setOnClickListener {
            val capturar = if ((Math.random() < 0.5)) 0 else 1

            if (capturar == 0){
                Toast.makeText(context,"Pokémon capturado!", Toast.LENGTH_SHORT).show()
                dismiss()
            }
            else {
                Toast.makeText(context,"Pokémon fugiu!", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }
    }

}
