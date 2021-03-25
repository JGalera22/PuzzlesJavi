package com.naturesecurityvanguard.puzzles_Javi.retrofit

import com.naturesecurityvanguard.puzzles_Javi.data.poko.request.PuzzleRequest
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.DetallePuzzle
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Puzzle
import retrofit2.Call
import retrofit2.http.*

interface PuzzleService {

    @GET("puzzles")
    fun getPuzzleList(@Header("Authorization") token: String?,
                        @Query("cat") paramCategoria : String?): Call<List<Puzzle>>

    @GET("puzzles/{id}")
    fun getDetallePuzzle(@Path("id") paramid: Long): Call<DetallePuzzle>

    @POST("puzzles")
    fun create(@Body nuevoPuzzle: PuzzleRequest, @Header("Authorization") token: String) : Call<DetallePuzzle>

    @DELETE("puzzles/{id}")
    fun deletePuzzle(@Header("Authorization") token: String?, @Path("id") paramId: Long): Call<Any>

    @PUT("puzzles/{id}")
    fun updatePuzzle(@Body editPuzzleRequest: PuzzleRequest, @Header("Authorization") token: String, @Path("id") paramId:Long): Call<DetallePuzzle>

    @POST("puzzles/deseado/{id}")
    fun createPuzzleDeseado(@Header("Authorization") token: String?, @Path("id") idPuzzle: Long) : Call<Puzzle>

    @DELETE("puzzles/deseado/{id}")
    fun deletePuzzleDeseado(@Header("Authorization") token: String?, @Path("id") idPuzzle: Long) : Call<Any>

    @GET("puzzles/deseado")
    fun getListaPuzzlesDeseados(@Header("Authorization")token: String?): Call<List<Puzzle>>

}