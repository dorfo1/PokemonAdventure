package br.com.fiap.pokemonmobileadventure.ui.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.ui.main.MainActivity
import br.com.fiap.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth


class SplashScreen : AppCompatActivity() {


    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        if (mAuth.currentUser != null) {
            mudarTela(true)
        }else{
            mudarTela(false)
        }
    }

    private fun mudarTela(usuarioLogado : Boolean) {
        var runnable= object : Runnable {
            override fun run() {
                if(usuarioLogado){
                    var intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    var intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        Handler().postDelayed(runnable,2000)
    }
}
