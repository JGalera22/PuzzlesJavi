package com.naturesecurityvanguard.puzzles_Javi.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.naturesecurityvanguard.puzzles_Javi.MainActivity
import com.naturesecurityvanguard.puzzles_Javi.R
import com.naturesecurityvanguard.puzzles_Javi.data.poko.request.LoginRequest
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.LoginResponse
import com.naturesecurityvanguard.puzzles_Javi.retrofit.AuthService
import com.naturesecurityvanguard.puzzles_Javi.ui.ListaPuzzles.FilterActivity
import com.naturesecurityvanguard.puzzles_Javi.ui.registro.RegistroActivity
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity() : AppCompatActivity() {


    var baseUrl = "http://10.0.2.2:9000/"


    lateinit var retrofit: Retrofit
    lateinit var service: AuthService
    val context = this


    lateinit var btnLogin: Button
    lateinit var btnInvitado: Button
    lateinit var editTextUsername: EditText
    lateinit var editTextPass: EditText
    lateinit var textGoSignUp: TextView
    lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        btnLogin = findViewById(R.id.button_añadir_deseados)
        btnInvitado = findViewById(R.id.button_invitado)
        editTextUsername = findViewById(R.id.username)
        editTextPass = findViewById(R.id.password)
        textGoSignUp = findViewById(R.id.text_view_go_signup)

        supportActionBar?.hide()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(AuthService::class.java)

        btnLogin.setOnClickListener(View.OnClickListener {

            doLogin()

        })
        btnInvitado.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        })

        textGoSignUp.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, RegistroActivity::class.java)
            this.startActivity(intent)
        })

    }

    fun doLogin() {

        val loginData = LoginRequest(editTextUsername.text.toString(), editTextPass.text.toString())

        service.login(loginData).enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onResponse(call: retrofit2.Call<LoginResponse>, response: Response<LoginResponse>) {
            Log.i("Datos Login", loginData.toString())
                if(response.code() == 201) {

                    var intent = Intent(context, FilterActivity::class.java)
                    context.startActivity(intent)
                    //TODO Guardar el token
                    val sharedPref =
                        context.getSharedPreferences("FILE_PREFERENCES", Context.MODE_PRIVATE)

                    with(sharedPref.edit()) {

                        putString("token", response.body()?.token)
                        commit()
                    }

                    Log.i("Error", response.body()?.token.toString())

                    var token = sharedPref?.getString("token", "")

                    if(token!= ""){
                        Log.i("Hay token", "Si")
                        Log.i("Token", token.toString())
                    }
                    else{
                        Log.i("Hay token", "No")
                    }

                    Log.i("Entra en el bucle", response.body()?.token.toString())

                } else {

                    Toast.makeText(context, "Login incorrecto", Toast.LENGTH_SHORT).show()

                    Log.i("MIGUEL", "Login incorrecto")

                    Log.i("Despacito", response.code().toString())

                }
            }

            override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                Log.i("MIGUEL", t.message.toString())

            }
        })

    }


}