package com.naturesecurityvanguard.puzzles_Javi.ui.ListaPuzzles

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.naturesecurityvanguard.puzzles_Javi.MainActivity
import com.naturesecurityvanguard.puzzles_Javi.R


class FilterActivity : AppCompatActivity(
) {
    lateinit var categoriaSpiner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        var boton:Button = findViewById(R.id.button_filtrar)
        val spinner: Spinner = findViewById(R.id.spiner)

        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }



        boton.setOnClickListener(View.OnClickListener {

            var intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)

            val categoria: String = categoriaSpiner.selectedItem.toString().toLowerCase()

            val sharedPref = this.getSharedPreferences("FILE_PREFERENCES", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("categoria", categoria)
                commit()
            }
        })

    }

}