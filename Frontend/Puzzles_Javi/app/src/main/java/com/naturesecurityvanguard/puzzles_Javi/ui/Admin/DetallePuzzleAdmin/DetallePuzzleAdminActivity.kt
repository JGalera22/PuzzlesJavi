package com.naturesecurityvanguard.puzzles_Javi.ui.Admin.DetallePuzzleAdmin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import coil.load
import com.naturesecurityvanguard.puzzles_Javi.R
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.DetallePuzzle
import com.naturesecurityvanguard.puzzles_Javi.retrofit.PuzzleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetallePuzzleAdminActivity : AppCompatActivity() {
    private var _detallePuzzleAdmin = MutableLiveData<DetallePuzzle>()
    private val baseUrl = "http://10.0.2.2:9000/"
    lateinit var service: PuzzleService
    lateinit var retrofit: Retrofit
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

        /*val sharedPref = this.getSharedPreferences("FILE_PREFERENCES", Context.MODE_PRIVATE)
        token = sharedPref?.getString("token", "")*/

        setContentView(R.layout.activity_detalle_puzzle_admin)
        puzzleId = intent.extras?.getLong("puzzleId")

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PuzzleService::class.java)


        getDetallePuzzle()

        titleView = findViewById(R.id.textView_titulo)
        categoriaView = findViewById(R.id.text_view_categoria_admin)
        precioView = findViewById(R.id.textView_precio)
        descripcionView = findViewById(R.id.textView_descripcion)
        numeroPiezasView = findViewById(R.id.textView_numeroPiezas)
        imageView = findViewById(R.id.imageView_foto_principal)
        btn = findViewById(R.id.button_editar)
        btn2 = findViewById(R.id.button_eliminar)

    }

    fun cambiarTitulo(titulo: String?) {
        title = titulo
    }


    fun getDetallePuzzle() {
        service.getDetallePuzzle(puzzleId!!.toLong()).enqueue(object :
            Callback<DetallePuzzle> {

            override fun onResponse(call: Call<DetallePuzzle>, response: Response<DetallePuzzle>) {
                if (response.code() == 200){
                    _detallePuzzleAdmin.value = response.body()

                    cambiarTitulo(_detallePuzzleAdmin.value?.nombre)
                    titleView.text = _detallePuzzleAdmin.value?.nombre
                    categoriaView.text = _detallePuzzleAdmin.value?.categoria
                    precioView.text = _detallePuzzleAdmin.value?.precio.toString()+ "€"
                    descripcionView.text = _detallePuzzleAdmin.value?.descripcion
                    numeroPiezasView.text = _detallePuzzleAdmin.value?.numeroPiezas.toString()
                    var imagenes = _detallePuzzleAdmin.value?.imagenes
                    if(imagenes!!.isNotEmpty()){
                        imageView.load(_detallePuzzleAdmin.value?.imagenes!![0].url)
                    }

                }
            }

            override fun onFailure(call: Call<DetallePuzzle>, t: Throwable) {

                Log.e("Error!!!", t.message.toString())
            }

        })
    }
}