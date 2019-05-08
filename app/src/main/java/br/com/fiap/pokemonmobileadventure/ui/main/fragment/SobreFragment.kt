package br.com.fiap.ui.Main.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.utils.Headers
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_sobre.*

class SobreFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_sobre,container,false);
        Glide.with(this)
            .load(Headers.getUrlWithHeaders("https://png2.kisspng.com/sh/4372b826a035fadecbcf39af4e4e62b1/L0KzQYm3VcI5N5drj5H0aYP2gLBuTgBqc5JogOc2cHn7db20ggJ1NWZnSNQEOXO7cYm4UcI6NmY8SKkAMEm8QYa5V8Q5Pmo3SKgDOESxgLBu/kisspng-pikachu-pixel-art-5b0b99c8a81129.5707509915274869206884.png"))
            .into(view.findViewById(R.id.img_sobre))


        return view
    }
}