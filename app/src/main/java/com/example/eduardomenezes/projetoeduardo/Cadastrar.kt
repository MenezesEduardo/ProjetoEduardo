package com.example.eduardomenezes.projetoeduardo

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.cadastrar.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.perfil.*


class Cadastrar : AppCompatActivity() {

    var email : String = ""
    var senha : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastrar)

        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "room-database"
        ).allowMainThreadQueries().build()



        btnCadastro.setOnClickListener {
            var currentEmail = db.emDao().getEmail(txtEmail.text.toString())
            var currentAluno = db.alDao().getAlunoByEmail(txtEmail.text.toString())

           // if ((currentEmail.email_current == null || currentAluno.email == null)){
                if(VerificaCampos()){
                    email = txtEmail.text.toString()
                    val aluno = Aluno (uid = 0,nome = "", telefone = "", matricula = "", email = email,senha = senha, imagem = "")
                    val current = CurrentEmail(email_current = email)
                    db.alDao().insertAluno(aluno)
                    db.emDao().insertEmail(current)
                    Toast.makeText(this@Cadastrar, "Aluno cadastrado com sucesso!", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@Cadastrar, Login::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this@Cadastrar, "Validação falhou", Toast.LENGTH_LONG).show()
                }
           /*}else{
                Toast.makeText(this@Cadastrar, "email já cadastrado", Toast.LENGTH_LONG).show()
            }*/
        }
    }

    fun VerificaCampos(): Boolean{

        var verificado: Boolean

        if (txtSenha.text.toString().trim().isNotEmpty()) {
            if (txtConfirma.text.toString().trim().isNotEmpty()){
                if (txtSenha.text.toString() == txtConfirma.text.toString()){
                    senha = txtSenha.text.toString()
                    verificado = true
                }else{
                    Toast.makeText(this@Cadastrar, "Senha e confirmar senha não são iguais.", Toast.LENGTH_LONG).show()
                    verificado = false
                }
            }else{
                Toast.makeText(this@Cadastrar, "Insira Confirmar Senha.", Toast.LENGTH_LONG).show()
                verificado = false
            }
        } else {
            Toast.makeText(this@Cadastrar, "Insira Senha.", Toast.LENGTH_LONG).show()
            verificado = false
        }
        return verificado
    }
}