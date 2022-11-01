package com.example.sqliteteste3

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // método para criar o banco com o SQlite
    override fun onCreate(db: SQLiteDatabase) {
        // sqlite query, com nomes das colunas
        // junto com os tipos de dados das colunas
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NOME_COl + " TEXT," +
                IDADE_COL + " TEXT" + ")")

        // chamada do SQlite
        // metodo para executar a query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // metodo para checar se a tabela ja existe
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // metodo para adicionar dados no banco
    fun addDados(nome : String, idade : String ){

        // variavel com o conteudo dos nossos valores
        val values = ContentValues()

        // Inserindo os valores na forma de chave valor
        values.put(NOME_COl, nome)
        values.put(IDADE_COL, idade)

        // Criando uma variavel writable
        // que vai possibilitar receber nossos valores no banco
        val db = this.writableDatabase

        // valores sao inseridos no banco
        db.insert(TABLE_NAME, null, values)

        // fecha nosso banco
        db.close()
    }

    // metodo para receber os dados do banco em um cursor
    fun getDados(): Cursor? {

        // variavel readable do nosso banco
        // que permite que nos leiamos os valores
        val db = this.readableDatabase

        // codigo retorna os valores da query em forma de cursor
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }

    companion object{
        // variaveis definidas para uso no nosso banco

        // nome do banco
        private val DATABASE_NAME = "IFPB"

        // versao do banco
        private val DATABASE_VERSION = 1

        // nome da tabela
        val TABLE_NAME = "tb_alunos"

        // coluna id
        val ID_COL = "id"

        // coluna nome
        val NOME_COl = "nome"

        // coluna idade
        val IDADE_COL = "idade"
    }
}
