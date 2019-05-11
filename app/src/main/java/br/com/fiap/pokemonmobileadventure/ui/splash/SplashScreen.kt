package br.com.fiap.pokemonmobileadventure.ui.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.ui.main.MainActivity
import br.com.fiap.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash.*


class SplashScreen : AppCompatActivity() {


    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        if (mAuth.currentUser != null) {
            mudarTela(true)
            load()
        }else{
            mudarTela(false)
            load()
        }
    }

    private fun load() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.splash)
        animation.reset()
        splash_background.startAnimation(animation)

        Handler().postDelayed({
        }, 3500L)
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
