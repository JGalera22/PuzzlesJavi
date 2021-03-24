package com.naturesecurityvanguard.puzzles_Javi.ui.Admin.NuevoPuzzle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.naturesecurityvanguard.puzzles_Javi.R
import com.naturesecurityvanguard.puzzles_Javi.data.poko.request.PuzzleRequest
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.DetallePuzzle
import com.naturesecurityvanguard.puzzles_Javi.retrofit.PuzzleService
import com.naturesecurityvanguard.puzzles_Javi.ui.Admin.PantallaInicioAdmin.ZonaAdminFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PuzzleNuevoActivity : AppCompatActivity() {

    var baseUrl = "http://10.0.2.2:9000/"

    lateinit var retrofit: Retrofit
    lateinit var service: PuzzleService
    val context = this

    lateinit var editTitulo: EditText
    lateinit var editDescripcion: EditText
    lateinit var editPrecio: EditText
    lateinit var editNumPiezas: EditText
    lateinit var editCategoria: EditText
    lateinit var btnCrear: Button
    lateinit var token: String
    var editar: Boolean = false
    var _puzzleId : Long? = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puzzle_nuevo)

        editar = intent.getBooleanExtra("editar", false)
        _puzzleId = intent.extras?.getLong("puzzleId")


        editTitulo = findViewById(R.id.editText_titulo)
        editDescripcion = findViewById(R.id.ediText_descripcion)
        editPrecio = findViewById(R.id.editNumber_precio)
        editNumPiezas = findViewById(R.id.editNumber_numPiezas)
        editCategoria = findViewById(R.id.editText_categoria)
        btnCrear = findViewById(R.id.btn_Guardar)

        if(editar) {
            cambiarTitulo("Editar Puzzle")
        }else{
            cambiarTitulo("Nuevo Puzzle")
        }
        val sharedPref = context?.getSharedPreferences("FILE_PREFERENCES", Context.MODE_PRIVATE)
        token = sharedPref?.getString("token", "").toString()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PuzzleService::class.java)

        btnCrear.setOnClickListener(View.OnClickListener {
            doCreate()
        })
    }

    fun cambiarTitulo(titulo: String?) {
        title = titulo
    }


    fun doCreate(){

        val puzzleData = PuzzleRequest(
            editTitulo.text.toString(),
            editCategoria.text.toString(),
            editDescripcion.text.toString(),
            editPrecio.text.toString().toDouble(),
            editNumPiezas.text.toString().toLong(),
        )
        if (!editar) {


            service.create(puzzleData, "Bearer $token")
                .enqueue(object : Callback<DetallePuzzle> {
                    override fun onResponse(
                        call: Call<DetallePuzzle>,
                        response: Response<DetallePuzzle>
                    ) {
                        if (response.code() == 201) {

                            Toast.makeText(context, "Guardado correctamente", Toast.LENGTH_SHORT)
                                .show()
                            var intent = Intent(context, ZonaAdminFragment::class.java)
                            context.startActivity(intent)
                        } else {
                            Toast.makeText(context, "No se ha podido guardar", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<DetallePuzzle>, t: Throwable) {
                        Log.e("Error!!!", t.message.toString())
                    }
                })

        }else{

            service.updatePuzzle(puzzleData,"Bearer $token", _puzzleId!!.toLong())
                .enqueue(object : Callback<DetallePuzzle> {
                    override fun onResponse(call: Call<DetallePuzzle>, response: Response<DetallePuzzle>) {
                        //Se edita pero hay que actualizar
                        if (response.code() == 200) {

                            Toast.makeText(context, "Guardado correctamente", Toast.LENGTH_SHORT)
                                .show()
                            var intent = Intent(context, ZonaAdminFragment::class.java)
                            context.startActivity(intent)

                        } else {
                            Toast.makeText(context, "No se ha podido guardar", Toast.LENGTH_SHORT)
                                .show()
                            Log.i("CODIGO ERROR:", response.code().toString())
                        }
                        Log.i("CODIGO ERROR:", response.code().toString())
                    }

                    override fun onFailure(call: Call<DetallePuzzle>, t: Throwable) {
                        Log.e("Error!!!", t.message.toString())
                    }

                })
        }

    }



}