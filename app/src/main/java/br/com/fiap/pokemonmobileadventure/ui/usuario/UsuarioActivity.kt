package br.com.fiap.pokemonmobileadventure.ui.usuario


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import br.com.fiap.model.User
import br.com.fiap.pokemonmobileadventure.R
import kotlinx.android.synthetic.main.activity_usuario.*

class UsuarioActivity : AppCompatActivity() {
//    TODO Criar alguma interação entre os usuários

    private var REQUEST_CALL: Int = 1
    //val trainer = getIntent().getExtras().getSerializableExtra("Usuario") as? User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        btn_ligar.setOnClickListener {
            fazerLigacao()
        }
    }

    private fun fazerLigacao() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL)

        } else {

            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 987809188))
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
