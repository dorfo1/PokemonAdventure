package br.com.fiap.ui.login

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.model.Pokemon
import br.com.fiap.model.User
import br.com.fiap.pokemonmobileadventure.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        mAuth = FirebaseAuth.getInstance()

        btCriar.setOnClickListener {
            cadastrarUsuario()
        }
    }



    private fun cadastrarUsuario() {
        mAuth.createUserWithEmailAndPassword(etEmail.text.toString(), etSenha.text.toString())
            .addOnCompleteListener{
                if(it.isSuccessful){
                    salvaNoRealtimeDatabase(User(etNome.text.toString(),etEmail.text.toString(),etTelefone.text.toString(),ArrayList<Pokemon>()));
                }else{
                    Toast.makeText(applicationContext,it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun salvaNoRealtimeDatabase(user: User) {
        var FB_DB = FirebaseDatabase.getInstance().getReference("Usuario")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(user).addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(applicationContext,"Usuário cadastrado com sucesso",Toast.LENGTH_LONG).show()
                    var intent = Intent()
                    intent.putExtra("email",user.email)
                    intent.putExtra("senha",etSenha.text.toString())
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }else{
                    Toast.makeText(applicationContext,it.exception?.message,Toast.LENGTH_LONG).show()
                }
            }
    }
}
