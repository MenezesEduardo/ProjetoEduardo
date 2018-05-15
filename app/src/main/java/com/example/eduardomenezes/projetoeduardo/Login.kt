package com.example.eduardomenezes.projetoeduardo

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.cadastrar.*
import kotlinx.android.synthetic.main.login.*


class Login : AppCompatActivity() {

    var email: String = ""
    var senha: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        supportActionBar?.hide()

        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "room-database"
        ).allowMainThreadQueries().build()

        // botões
        // -----------------------
        btnEntrar.setOnClickListener {

            if(TxtEmail.text.toString().isNotEmpty() && TxtEmail.text.toString().isNotEmpty()) {
                var currentAluno = db.alDao().getAlunoByEmailAndSenha(TxtEmail.text.toString(), TxtSenha.text.toString())
                email = try { currentAluno.email.toString()} catch (e: Exception){""}
                senha = try { currentAluno.senha.toString()} catch (e: Exception){""}
            }
            if (email != "") {
                val intent = Intent(this@Login, ListaNoticias::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this@Login, "Validação falhou", Toast.LENGTH_LONG).show()
            }
        }

        linkCadastrar.setOnClickListener {
            //navega a proxima activity
            val intent = Intent(this@Login, Cadastrar::class.java)
            startActivity(intent)
        }

        linkRecuperar.setOnClickListener {
            //navega a proxima activity
            val intent = Intent(this@Login, RecuperarSenha::class.java)
            startActivity(intent)
        }
    }
}