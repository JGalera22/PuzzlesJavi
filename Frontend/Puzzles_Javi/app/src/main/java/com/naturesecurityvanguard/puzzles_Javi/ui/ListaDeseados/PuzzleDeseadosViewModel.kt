package com.naturesecurityvanguard.puzzles_Javi.ui.ListaDeseados

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Puzzle
import com.naturesecurityvanguard.puzzles_Javi.retrofit.PuzzleService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PuzzleDeseadosViewModel(application: Application): AndroidViewModel(application) {
    private val _puzzlesDeseados = MutableLiveData<List<Puzzle>>()
    private val baseUrl = "http://10.0.2.2:9000/"
    private var service: PuzzleService
    var token: String?



    private val context = getApplication<Application>().applicationContext

    val puzzlesDeseados: LiveData<List<Puzzle>>
        get() = _puzzlesDeseados


    init {
        _puzzlesDeseados.value = listOf()
        val sharedPref = context?.getSharedPreferences("FILE_PREFERENCES", Context.MODE_PRIVATE)
        token = sharedPref?.getString("token", "")


        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        }.build()

        Log.i("Tokeeeen!!!!!", token.toString())


        var retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PuzzleService::class.java)


        getListaPuzzlesDeseados()

    }



    fun getListaPuzzlesDeseados() {

        service.getListaPuzzlesDeseados("Bearer $token")
            .enqueue(object : Callback<List<Puzzle>> {

                override fun onResponse(call: Call<List<Puzzle>>, response: Response<List<Puzzle>>) {
                    if (response.code() == 200){
                        _puzzlesDeseados.value = response.body()
                        Log.i("Puzles: ", _puzzlesDeseados.value.toString())

                    }
                    if(response.code() == 404){
                        Toast.makeText(context, "No se ha encontrado ningun puzzle", Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<List<Puzzle>>, t: Throwable) {

                    Log.e("Error!!!", t.message.toString())
                }
            })
    }
}