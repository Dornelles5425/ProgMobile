package com.example.sqliteteste3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Chamar o listener onClick do nosso botao addDados
        addDados.setOnClickListener{

            // Criar uma classe BDHelper e passar o context
            val db = DBHelper(this, null)

            // variaveis para os valores dos edit texts
            val nome = enterNome.text.toString()
            val idade = enterIdade.text.toString()

            // chamar o metodo addDados para adicionar dados no nosso banco
            db.addDados(nome, idade)

            // Toast para mostrar uma mensagem na tela
            Toast.makeText(this, nome + " adicionado com Sucesso!", Toast.LENGTH_LONG).show()

            // limpar os edits text
            enterNome.text.clear()
            enterIdade.text.clear()
        }

        // codigo para mostrar nossos dados quando clicar no botao
        printDados.setOnClickListener{

            //Limpa os dados anteriores
            Nome.setText("");
            Idade.setText("");


            // Criar uma classe BDHelper e passar o context
            val db = DBHelper(this, null)

            // variavel cursor que chama o metodo getDados
            // vai retornar os valores em forma de cursor
            val cursor = db.getDados()

            // movendo o cursor para o primeiro valor e mostrando na lista
            cursor!!.moveToFirst()
            Nome.append(cursor.getString(cursor.getColumnIndex(DBHelper.NOME_COl)) + "\n")
            Idade.append(cursor.getString(cursor.getColumnIndex(DBHelper.IDADE_COL)) + "\n")

            // movendo o cursor para o resto dos valores
            while(cursor.moveToNext()){
                Nome.append(cursor.getString(cursor.getColumnIndex(DBHelper.NOME_COl)) + "\n")
                Idade.append(cursor.getString(cursor.getColumnIndex(DBHelper.IDADE_COL)) + "\n")
            }

            // fechando o cursor
            cursor.close()
        }
    }
}
