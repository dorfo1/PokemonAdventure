package br.com.fiap.pokemonmobileadventure.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat

object PermissionUtils{

    fun validarPermissoes(permissoes: List<String>, activity: FragmentActivity?, requestCode:Int) : Boolean{

        val listaPermissoes = ArrayList<String>()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permissao in permissoes){
                val temPermissao = ContextCompat.checkSelfPermission(activity as Activity,permissao) == PackageManager.PERMISSION_GRANTED
                if(!temPermissao) listaPermissoes.add(permissao)
            }

            if(listaPermissoes.isEmpty()) return true
            else{
                ActivityCompat.requestPermissions(activity as Activity,listaPermissoes.toTypedArray(),requestCode)
            }
        }
        return true
    }
}