package com.example.eduardomenezes.projetoeduardo

import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.lista_noticia.*
import java.util.*

class ListaNoticias: AppCompatActivity() {

    private var listNoticias = ArrayList<Noticia>()

    var noticiaAdapter = NoticiasAdapter(this, listNoticias)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_noticia)

        //Carregar noticias ---------------------------------

        listNoticias.add(Noticia(0,"Lorem ipsum dolor sit amet","Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Ut vel malesuada nunc, n" +
                "ec mattis risus. Proin pulvinar nec metus auctor gravida. " +
                "Quisque diam nisl, efficitur non aliquam nec, accumsan in urna." +
                " Nam sollicitudin ligula vitae felis mattis," +
                " id dictum nibh interdum. Pellentesque ligula elit, ","15/05/2018",""))
        listNoticias.add(Noticia(1,"Mauris ut orci ut sapien vehicula","Mauris ut orci ut sapien vehicula dignissim at at nisi." +
                " In tempus eu lectus feugiat aliquet. " +
                "Suspendisse hendrerit ornare urna sit amet fringilla.","16/05/2018",""))
        listNoticias.add(Noticia(2,"Morbi convallis justo quis turpis","Morbi convallis justo quis turpis malesuada vehicula vitae et ligula." +
                " Integer faucibus rutrum iaculis. Mauris at erat eu metus pretium aliquam id a ex." +
                " Nullam hendrerit eu nisi ut volutpat. Curabitur sodales imperdiet eros ac congue.","17/05/2018",""))
        listNoticias.add(Noticia(3,"Duis eu ex vitae felis","Duis eu ex vitae felis viverra feugiat. Maecenas tristique sagittis nunc," +
                " et volutpat risus tristique vel. " +
                "Cras gravida ex non elit blandit, sed pellentesque eros congue.","18/05/2018",""))
        listNoticias.add(Noticia(4,"Aenean imperdiet mattis placerat"," Aenean imperdiet mattis placerat. Nam congue diam urna, ut blandit risus commodo at. " +
                "Integer congue convallis odio vel pellentesque. Cras vel pharetra dui. " +
                "Aenean rhoncus nulla non velit laoreet dapibus. Nulla ut sodales odio." +
                "Integer vestibulum et dolor non faucibus.In velit ex, imperdiet id lacus vitae, interdum viverra metus. " +
                "Fusce scelerisque viverra imperdiet. Aenean non odio dapibus, scelerisque leo sed, " +
                "faucibus diam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; " +
                "Pellentesque egestas ultricies leo, a semper nisl laoreet sollicitudin. ","19/05/2018",""))

        //------------------------------------------------------------------
        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "room-database"
        ).allowMainThreadQueries().build()

        var allNoticias = db.notDao().getAllNoticias()
        for (notic in allNoticias){
            listNoticias.add(Noticia(notic.uid ,notic.titulo, notic.texto, notic.data, notic.imagem))
        }
        noticiaAdapter = NoticiasAdapter(this, listNoticias)
        lv_lista.adapter = noticiaAdapter
        lv_lista.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val idnot =  listNoticias[position].uid.toString()
            val intent = Intent(this@ListaNoticias, DetalheNoticias::class.java)
            //intent.putExtra( "noticiaId", idnot)
            intent.putExtra("titulo", listNoticias[position].titulo)
            intent.putExtra("texto", listNoticias[position].texto)
            intent.putExtra("data", listNoticias[position].data)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id: Int = item!!.itemId
        if (id == R.id.cadastrar_item){
            Toast.makeText(this,"test",Toast.LENGTH_LONG)
            val intent = Intent(this@ListaNoticias, Perfil::class.java)
            startActivity(intent)

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    inner class NoticiasAdapter : BaseAdapter {

        private var listNoticias = ArrayList<Noticia>()
        private var context: Context? = null
        constructor(context: Context, listNoticias : ArrayList<Noticia>) : super() {
            this.listNoticias = listNoticias
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder
            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.item_noticia, parent, false)
                vh = ViewHolder(view)
                view.tag = vh

            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }
            vh.textView_texto.text = listNoticias[position].texto
            vh.textView_titulo.text = listNoticias[position].titulo
            vh.textView_data.text = listNoticias[position].data
            return view
        }

        override fun getItem(position: Int): Any {
            return listNoticias[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listNoticias.size
        }
    }

    private class ViewHolder(view: View?) {
        val textView_titulo: TextView
        val textView_texto: TextView
        val textView_data: TextView

        init {
            this.textView_titulo = view?.findViewById<TextView>(R.id.tvTitulo) as TextView
            this.textView_texto = view?.findViewById<TextView>(R.id.tvTexto) as TextView
            this.textView_data = view?.findViewById<TextView>(R.id.tvData) as TextView
        }
    }

}



