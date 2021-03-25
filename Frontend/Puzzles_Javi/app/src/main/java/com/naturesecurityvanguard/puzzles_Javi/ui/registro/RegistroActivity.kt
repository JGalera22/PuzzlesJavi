package com.naturesecurityvanguard.puzzles_Javi.ui.registro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.naturesecurityvanguard.puzzles_Javi.R
import com.naturesecurityvanguard.puzzles_Javi.data.poko.request.UsuarioRequest
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.UsuarioRegistroResponse
import com.naturesecurityvanguard.puzzles_Javi.retrofit.AuthService
import com.naturesecurityvanguard.puzzles_Javi.ui.login.LoginActivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RegistroActivity : AppCompatActivity() {


    var baseUrl = "http://10.0.2.2:9000/auth/"

    lateinit var retrofit: Retrofit
    lateinit var service: AuthService
    val context = this

    lateinit var btnRegistro: Button
    lateinit var editTextPassword: EditText
    lateinit var editTextEmail: EditText
    lateinit var editTextUsername: EditText
    lateinit var editTextFullName: EditText
    lateinit var textGoLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        supportActionBar?.hide()


        editTextUsername = findViewById(R.id.edit_text_username)
        editTextPassword = findViewById(R.id.edit_text_password)
        editTextEmail = findViewById(R.id.edit_text_email)
        editTextFullName = findViewById(R.id.edit_text_fullname)
        textGoLogin = findViewById(R.id.text_view_go_login)
        btnRegistro = findViewById(R.id.button_registro)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging) // <-- this is the important line!

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl).client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(AuthService::class.java)

        btnRegistro.setOnClickListener(View.OnClickListener {
            doSignUp(UsuarioRequest(editTextEmail.text.toString(),
                    editTextFullName.text.toString(), editTextPassword.text.toString(), editTextUsername.text.toString()))
        })

        textGoLogin.setOnClickListener(View.OnClickListener {
            var intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        })
    }

    fun doSignUp(usuarioRequest: UsuarioRequest) {
        /*val registerData = UsuarioRequest(editTextEmail.text.toString(),
                editTextFullName.text.toString(), editTextPassword.text.toString(), editTextUsername.text.toString())*/

        service.registroUsuario(usuarioRequest).enqueue(object : Callback<UsuarioRegistroResponse> {

            override fun onResponse(call: Call<UsuarioRegistroResponse>, response: Response<UsuarioRegistroResponse>) {
                if (response.code() == 201) {
                    Toast.makeText(context, "Registro realizado correctamente", Toast.LENGTH_SHORT).show()
                    var intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "No se ha podido registrar", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UsuarioRegistroResponse>, t: Throwable) {
                Log.i("Error", t.message.toString())
            }

        })
    }
}