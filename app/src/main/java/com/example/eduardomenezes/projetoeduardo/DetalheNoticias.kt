package com.example.eduardomenezes.projetoeduardo

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.detalhes_noticia.*
import java.util.ArrayList

class DetalheNoticias: AppCompatActivity() {
    private var listNoticias = ArrayList<Noticia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhes_noticia)

       // val selectedNoticia = intent.getStringExtra("noticiaId").toInt()
        val texto = intent.getStringExtra("texto")
        val titulo = intent.getStringExtra("titulo")
        val datanoticia = intent.getStringExtra("data")
        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "room-database"
        ).allowMainThreadQueries().build()

        this.tvData.text = datanoticia
        this.tvTexto.text = texto
        this.tvTitulo.text = titulo
        /*
        o get por id não está funcionando, não sei pq.
        var currentNoticia = db.notDao().getNoticiaById(selectedNoticia)
        this.tvData.text = currentNoticia.data
        this.tvTexto.text = currentNoticia.texto
        this.tvTitulo.text = currentNoticia.titulo
*/
    }
}