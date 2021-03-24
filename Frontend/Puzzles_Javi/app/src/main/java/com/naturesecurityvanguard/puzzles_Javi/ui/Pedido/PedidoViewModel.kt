package com.naturesecurityvanguard.puzzles_Javi.ui.Pedido

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.LineaPedido
import com.naturesecurityvanguard.puzzles_Javi.retrofit.PedidoService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PedidoViewModel (application: Application) : AndroidViewModel(application) {

    private val _pedido = MutableLiveData<List<LineaPedido>>()

    private val baseUrl = "http://10.0.2.2:9000/"

    private var service: PedidoService
    var token: String?


    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    val pedido: LiveData<List<LineaPedido>>
        get() = _pedido


    init {
        _pedido.value = listOf()
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

        service = retrofit.create(PedidoService::class.java)


        getPedidoList()

    }


    fun getPedidoList() {

        service.getPedidoList("Bearer $token")
                .enqueue(object : Callback<List<LineaPedido>> {

                    override fun onResponse(call: Call<List<LineaPedido>>, response: Response<List<LineaPedido>>) {
                        if (response.code() == 200) {
                            _pedido.value = response.body()
                            Log.i("Pedido: ", _pedido.value.toString())

                        }
                        if (response.code() == 404) {
                            Toast.makeText(context, "No se ha encontrado ningun pedido", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onFailure(call: Call<List<LineaPedido>>, t: Throwable) {

                        Log.e("Error!!!", t.message.toString())
                    }
                })
    }
}

