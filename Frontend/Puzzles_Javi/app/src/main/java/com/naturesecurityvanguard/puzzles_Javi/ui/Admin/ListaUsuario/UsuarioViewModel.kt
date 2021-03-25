package com.naturesecurityvanguard.puzzles_Javi.ui.Admin.ListaUsuario

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.*
import com.naturesecurityvanguard.puzzles_Javi.retrofit.UsuarioService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsuarioViewModel (application: Application) : AndroidViewModel(application){

    private val _usuario = MutableLiveData<List<UsuarioDetalleList>>()
    private val baseUrl = "http://10.0.2.2:9000/"
    private var service: UsuarioService
    var token: String?

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    val usuario: LiveData<List<UsuarioDetalleList>>
        get() = _usuario


    init {
        _usuario.value = listOf()
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

        service = retrofit.create(UsuarioService::class.java)


        getAllUser()

    }


    fun getAllUser() {
        service.getAllUser("Bearer $token")
        .enqueue(object : Callback<List<UsuarioDetalleList>> {

                override fun onResponse(call: Call<List<UsuarioDetalleList>>, response: Response<List<UsuarioDetalleList>>) {
                    if (response.code() == 200){
                        _usuario.value = response.body()
                        Log.i("usuario: ", _usuario.value.toString())

                    }
                    if(response.code() == 404){
                        Toast.makeText(context, "No se ha encontrado ningun usuario", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<UsuarioDetalleList>>, t: Throwable) {

                    Log.e("Error!!!", t.message.toString())
                }
            })
    }


}

