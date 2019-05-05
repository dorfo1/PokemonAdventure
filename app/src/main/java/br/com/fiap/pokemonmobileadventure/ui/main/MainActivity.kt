package br.com.fiap.pokemonmobileadventure.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.ui.Main.Fragment.MapaFragment
import br.com.fiap.ui.Main.Fragment.PokedexFragment
import br.com.fiap.ui.Main.Fragment.SobreFragment
import br.com.fiap.ui.Main.Fragment.TimeFragment
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        var fragmentManager = supportFragmentManager
        var tx = fragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.navigation_pokedex -> {
                tx.replace(R.id.frame_principal,PokedexFragment())
                tx.commit()
            }
            R.id.navigation_time -> {
                tx.replace(R.id.frame_principal,TimeFragment())
                tx.commit()
            }
            R.id.navigation_mapa -> {
                tx.replace(R.id.frame_principal,MapaFragment())
                tx.commit()
            }
            R.id.navigation_sobre ->{
                tx.replace(R.id.frame_principal,SobreFragment())
                tx.commit()
            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val manager = supportFragmentManager
        val tx = manager.beginTransaction()
        tx.replace(R.id.frame_principal, MapaFragment())
        tx.commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_mapa


    }
}
