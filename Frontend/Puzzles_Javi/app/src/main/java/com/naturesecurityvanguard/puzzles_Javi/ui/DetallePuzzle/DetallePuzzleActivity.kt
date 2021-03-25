package com.naturesecurityvanguard.puzzles_Javi.ui.DetallePuzzle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import coil.load
import com.naturesecurityvanguard.puzzles_Javi.MainActivity
import com.naturesecurityvanguard.puzzles_Javi.R
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.DetallePuzzle
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Puzzle
import com.naturesecurityvanguard.puzzles_Javi.retrofit.PuzzleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetallePuzzleActivity : AppCompatActivity() {

    private var _detallePuzzle = MutableLiveData<DetallePuzzle>()
    private val baseUrl = "http://10.0.2.2:9000/"
    lateinit var service: PuzzleService
    lateinit var retrofit: Retrofit
    var token: String = ""
    var deseado: Boolean = false
    var puzzleId: Long? = 0

    lateinit var titleView: TextView
    lateinit var precioView: TextView
    lateinit var categoriaView: TextView
    lateinit var descripcionView: TextView
    lateinit var numeroPiezasView: TextView
    lateinit var imageView: ImageView
    lateinit var btn: Button
    lateinit var btn2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = this.getSharedPreferences("FILE_PREFERENCES", Context.MODE_PRIVATE)
        token = sharedPref?.getString("token", "")!!
        setContentView(R.layout.activity_detalle_puzzle)
        puzzleId = intent.extras?.getLong("puzzleId")

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PuzzleService::class.java)


        getDetallePuzzle()

        titleView = findViewById(R.id.textView_titulo)
        categoriaView = findViewById(R.id.textView_categoria)
        precioView = findViewById(R.id.textView_precio)
        descripcionView = findViewById(R.id.textView_descripcion)
        numeroPiezasView = findViewById(R.id.textView_numeroPiezas)
        imageView = findViewById(R.id.imageView_foto_principal)
        btn = findViewById(R.id.button_añadir_deseados)
        btn2 = findViewById(R.id.button_comprar)

        btn.setOnClickListener(View.OnClickListener {
            createPuzzleDeseado(puzzleId!!.toLong(), deseado)
        })

        btn2.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        })

    }

    fun cambiarTitulo(titulo: String?) {
        title = titulo
    }


    fun getDetallePuzzle() {
        service.getDetallePuzzle(puzzleId!!.toLong()).enqueue(object :
            Callback<DetallePuzzle> {

            override fun onResponse(call: Call<DetallePuzzle>, response: Response<DetallePuzzle>) {
                if (response.code() == 200){
                    _detallePuzzle.value = response.body()

                    cambiarTitulo(_detallePuzzle.value?.nombre)
                    titleView.text = _detallePuzzle.value?.nombre
                    categoriaView.text = _detallePuzzle.value?.categoria
                    precioView.text = _detallePuzzle.value?.precio.toString()+ "€"
                    descripcionView.text = _detallePuzzle.value?.descripcion
                    numeroPiezasView.text = _detallePuzzle.value?.numeroPiezas.toString()
                    var imagenes = _detallePuzzle.value?.imagenes
                    if(imagenes!!.isNotEmpty()){
                        imageView.load(_detallePuzzle.value?.imagenes!![0].url)
                    }

                }
            }

            override fun onFailure(call: Call<DetallePuzzle>, t: Throwable) {

                Log.e("Error!!!", t.message.toString())
            }

        })
    }

    fun createPuzzleDeseado(puzzleId: Long, deseado: Boolean) {

        if(!deseado){
            btn.setText("Añadir a Deseados")
            service.createPuzzleDeseado("Bearer $token", puzzleId).enqueue(object : Callback<Puzzle>{

                override fun onResponse(call: Call<Puzzle>, response: Response<Puzzle>) {
                    if(response.code() == 201){
                        getDetallePuzzle()

                    }
                }

                override fun onFailure(call: Call<Puzzle>, t: Throwable) {

                }

            })
        }
        else{
            btn.setText("Eliminar de Deseados")
            service.deletePuzzleDeseado("Bearer $token", puzzleId).enqueue(object : Callback<Any>{
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    if(response.code() == 204){
                        getDetallePuzzle()

                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {

                }

            })
        }


    }



}