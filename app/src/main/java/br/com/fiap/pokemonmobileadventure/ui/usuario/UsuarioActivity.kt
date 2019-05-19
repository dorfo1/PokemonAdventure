package br.com.fiap.pokemonmobileadventure.ui.usuario


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import br.com.fiap.model.User
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.adapter.PokedexAdapter
import br.com.fiap.pokemonmobileadventure.remote.TraderAPI
import br.com.fiap.pokemonmobileadventure.remote.TraderCliente
import br.com.fiap.pokemonmobileadventure.remote.getTraderAPI
import kotlinx.android.synthetic.main.activity_usuario.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioActivity : AppCompatActivity() {


    private var REQUEST_CALL: Int = 1
    private lateinit var usuario : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)
        supportActionBar?.hide()
        usuario = intent.getParcelableExtra("Usuario") as User

        tvUsuarioNome.text = usuario.nome
        tvUsuarioEmail.text = usuario.email

        rvPokemons.layoutManager = LinearLayoutManager(this)
        rvPokemons.adapter = PokedexAdapter(this,usuario.pokemons)

        fabLigar.setOnClickListener {
            fazerLigacao()
        }

        btnQueroTrocar.setOnClickListener {
           getTraderAPI().notificarTroca(usuario.nome,usuario.firebaseId).enqueue(object : Callback<Void>{
               override fun onFailure(call: Call<Void>, t: Throwable) {
                   Toast.makeText(applicationContext,"Falha ao notificar usuário",Toast.LENGTH_SHORT).show()
               }

               override fun onResponse(call: Call<Void>, response: Response<Void>) {
                   if(response.isSuccessful) Toast.makeText(applicationContext,usuario.nome+" foi notificado.",Toast.LENGTH_SHORT).show()
               }

           })
        }
    }

    private fun fazerLigacao() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL)
        } else {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + usuario.telefone))
            startActivity(intent)

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (result in grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "Permissão negada", Toast.LENGTH_LONG).show()
            } else {
                fazerLigacao()

            }
        }

    }
}
