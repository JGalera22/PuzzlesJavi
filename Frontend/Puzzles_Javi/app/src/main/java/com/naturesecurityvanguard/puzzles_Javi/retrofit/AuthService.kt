package com.naturesecurityvanguard.puzzles_Javi.retrofit
import com.naturesecurityvanguard.puzzles_Javi.data.poko.request.LoginRequest
import com.naturesecurityvanguard.puzzles_Javi.data.poko.request.UsuarioRequest
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.LoginResponse
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.UsuarioRegistroResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {


    @POST("register")
    fun registroUsuario(@Body newUser: UsuarioRequest): Call<UsuarioRegistroResponse>

    @POST("auth/login")
    fun login(@Body requestLogin: LoginRequest): Call<LoginResponse>

}