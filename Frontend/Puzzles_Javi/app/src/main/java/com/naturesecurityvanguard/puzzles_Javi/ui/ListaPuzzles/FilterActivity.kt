package com.naturesecurityvanguard.puzzles_Javi.ui.ListaPuzzles

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.naturesecurityvanguard.puzzles_Javi.MainActivity
import com.naturesecurityvanguard.puzzles_Javi.R


class FilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        var boton:Button = findViewById(R.id.button_filtrar)
        var categoria: EditText = findViewById(R.id.edit_categoria_filtro)

        boton.setOnClickListener(View.OnClickListener {

            var intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)

            var categoria: String = categoria.text.toString()


            val sharedPref = this.getSharedPreferences("FILE_PREFERENCES", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("categoria", categoria)
                commit()
            }

        })

    }

}