package com.naturesecurityvanguard.puzzles_Javi.retrofit

import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.LineaPedido
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Puzzle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PedidoService {

    @GET("puzzle")
    fun getPedidoList(@Header("Authorization") token: String?): Call<List<LineaPedido>>

}