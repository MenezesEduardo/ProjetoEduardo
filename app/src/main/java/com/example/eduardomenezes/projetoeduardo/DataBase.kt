package com.example.eduardomenezes.projetoeduardo

import android.arch.persistence.room.*
import android.provider.ContactsContract

@Entity(tableName = "tb_current_email")
data class CurrentEmail (
        @PrimaryKey(autoGenerate = true) var uid: Int = 0,
        @ColumnInfo(name = "email") var email_current: String?)

@Dao
interface  EmailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmail(email: CurrentEmail)

    @Update
    fun updateEmail(email: CurrentEmail)

    @Query("SELECT * FROM tb_current_email")
    fun getEmail(): CurrentEmail

    @Query("SELECT * FROM tb_current_email WHERE email LIKE :email ")
    fun getEmail(email: String): CurrentEmail
}

@Entity(tableName = "tb_aluno")
data class Aluno (
        @PrimaryKey(autoGenerate = true) var uid: Int,
        @ColumnInfo(name = "aluno_nome") var nome: String?,
        @ColumnInfo(name = "aluno_telefone") var telefone: String?,
        @ColumnInfo(name = "aluno_matricula") var matricula: String?,
        @ColumnInfo(name = "aluno_email") var email: String?,
        @ColumnInfo(name = "aluno_senha") var senha: String?,
        @ColumnInfo(name = "aluno_imagem") var imagem: String?)

@Dao
interface AlDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAluno(aluno: Aluno)

    @Update
    fun updateAluno(aluno: Aluno)

    @Query("SELECT * FROM tb_aluno")
    fun getAllAlunos(): Array<Aluno>

    @Query("SELECT * FROM tb_aluno WHERE aluno_email LIKE :email ")
    fun getAlunoByEmail(email: String): Aluno

    @Query("SELECT * FROM tb_aluno WHERE aluno_email LIKE :email AND aluno_senha LIKE :senha")
    fun getAlunoByEmailAndSenha(email: String, senha: String): Aluno
}

@Entity(tableName = "tb_noticia")
data class Noticia (
        @PrimaryKey(autoGenerate = true) var uid: Int = 0,
        @ColumnInfo(name = "noticia_titulo") var titulo: String?,
        @ColumnInfo(name = "noticia_texto") var texto: String?,
        @ColumnInfo(name = "noticia_data") var data: String?,
        @ColumnInfo(name = "noticia_imagem") var imagem: String?)

@Dao
interface NotDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNoticia(noti: Noticia)

    @Update
    fun updateNoticia(noti: Noticia)

    @Delete
    fun deleteNoticia(noti: Noticia)

    @Query("SELECT * FROM tb_noticia")
    fun getAllNoticias(): Array<Noticia>

    @Query("SELECT * FROM tb_noticia WHERE uid = :search")
    fun getNoticiaById(search: Int): Noticia

}

@Database(entities = arrayOf( Noticia::class, Aluno::class, CurrentEmail::class),  version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notDao(): NotDAO
    abstract fun alDao(): AlDao
    abstract fun emDao(): EmailDao
}