package com.naturesecurityvanguard.puzzles_Javi.retrofit


import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.UsuarioDetalleList
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.UsuarioRegistroResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header


interface UsuarioService {


    @GET("auth/user/me")
    fun getUser(@Header("Authorization")token: String?): Call<UsuarioRegistroResponse>

    @GET("/usuario")
    fun getAllUser(@Header("Authorization")token: String?): Call<List<UsuarioDetalleList>>


}