package com.example.eduardomenezes.projetoeduardo

import android.arch.persistence.room.Room
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.perfil.*
import kotlin.jvm.internal.Ref


class Perfil: AppCompatActivity()  {

    var nome : String = ""
    var matricula : String = ""
    var telefone : String = ""
    var email : String = ""
    var senha : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil)

        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "room-database"
        ).allowMainThreadQueries().build()

        var currentEmail = db.emDao().getEmail()
        var currentAluno = db.alDao().getAlunoByEmail(currentEmail.email_current.toString())
        this.txtNome.setText(currentAluno.nome, TextView.BufferType.EDITABLE)
        this.txtEmail2.setText(currentAluno.email, TextView.BufferType.EDITABLE)
        this.txtMatricula.setText(currentAluno.matricula, TextView.BufferType.EDITABLE)
        this.txtTelefone.setText(currentAluno.telefone, TextView.BufferType.EDITABLE)
        this.txtSenha2.setText(currentAluno.senha, TextView.BufferType.EDITABLE)


        imgPerfil.setOnClickListener{
            Toast.makeText(this@Perfil,"Teste Foto", Toast.LENGTH_LONG).show()
        }

        btnSalvar.setOnClickListener {

            if(VerificaCampos()){
                val aluno = Aluno (uid = currentAluno.uid,nome = nome, telefone = telefone, matricula = matricula, email = email,senha = senha, imagem = "")
                val current= CurrentEmail (uid = currentEmail.uid, email_current = email)
                db.alDao().updateAluno(aluno)
                db.emDao().updateEmail(current)

                Toast.makeText(this@Perfil, "Dados salvos com sucesso!", Toast.LENGTH_LONG).show()
                val intent = Intent(this@Perfil, Login::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this@Perfil, "Validação falhou", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun VerificaCampos(): Boolean{

        var verificado = false

        if (txtNome.text.toString().trim().isNotEmpty()) {
            nome = txtNome.text.toString()
            verificado = true
        } else {
            Toast.makeText(this@Perfil, "Insira nome.", Toast.LENGTH_LONG).show()
            verificado = false
        }

        if (txtTelefone.text.toString().trim().isNotEmpty()) {
            telefone = txtTelefone.text.toString()
            verificado = true
        } else {
            Toast.makeText(this@Perfil, "Insira telefone.", Toast.LENGTH_LONG).show()
            verificado = false
        }

        if (txtMatricula.text.toString().trim().isNotEmpty()) {
            matricula = txtMatricula.text.toString()
            verificado = true
        } else {
            Toast.makeText(this@Perfil, "Insira matricula.", Toast.LENGTH_LONG).show()
            verificado = false
        }

        if (txtEmail2.text.toString().trim().isNotEmpty()) {
            email = txtEmail2.text.toString()
            verificado = true
        } else {
            Toast.makeText(this@Perfil, "Insira email.", Toast.LENGTH_LONG).show()
            verificado = false
        }

        if (txtSenha2.text.toString().trim().isNotEmpty()) {
            if (txtConfirma2.text.toString().trim().isNotEmpty()){
                if (txtSenha2.text.toString() == txtConfirma2.text.toString()){
                    senha = txtSenha2.text.toString()
                    verificado = true
                }else{
                    Toast.makeText(this@Perfil, "Senha e confirmar senha não são iguais.", Toast.LENGTH_LONG).show()
                    verificado = false
                }
            }else{
                Toast.makeText(this@Perfil, "Insira Confirmar Senha.", Toast.LENGTH_LONG).show()
                verificado = false
            }
        } else {
            Toast.makeText(this@Perfil, "Insira Senha.", Toast.LENGTH_LONG).show()
            verificado = false
        }

        return verificado
    }
}