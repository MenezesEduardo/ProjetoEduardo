package com.example.eduardomenezes.projetoeduardo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.recuperar_senha.*


class RecuperarSenha : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recuperar_senha)

        btnResetar.setOnClickListener {
            //navega a proxima activity
            val intent = Intent(this@RecuperarSenha, Login::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent)
            finish()
        }

    }
}