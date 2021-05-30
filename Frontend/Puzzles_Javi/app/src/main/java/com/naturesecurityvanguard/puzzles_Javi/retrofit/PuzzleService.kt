package com.naturesecurityvanguard.puzzles_Javi.retrofit

import com.naturesecurityvanguard.puzzles_Javi.data.poko.request.PuzzleRequest
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.DetallePuzzle
import com.naturesecurityvanguard.puzzles_Javi.data.poko.response.Puzzle
import retrofit2.Call
import retrofit2.http.*

interface PuzzleService {

    @GET("puzzle")
    fun getPuzzleList(@Header("Authorization") token: String?,
                        @Query("cat") paramCategoria : String?): Call<List<Puzzle>>

    @GET("puzzle/admin")
    fun getPuzzleListAdmin(@Header("Authorization") token: String?,
                      @Query("cat") paramCategoria : String?): Call<List<Puzzle>>

    @GET("puzzle/{id}")
    fun getDetallePuzzle(@Path("id") paramid: Long): Call<DetallePuzzle>

    @POST("puzzle")
    fun create(@Body nuevoPuzzle: PuzzleRequest, @Header("Authorization") token: String) : Call<DetallePuzzle>

    @DELETE("puzzle/{id}")
    fun deletePuzzle(@Header("Authorization") token: String?, @Path("id") paramId: Long): Call<Any>

    @PUT("puzzle/{id}")
    fun updatePuzzle(@Body editPuzzleRequest: PuzzleRequest, @Header("Authorization") token: String, @Path("id") paramId:Long): Call<DetallePuzzle>

    @POST("puzzle/deseado/{id}")
    fun createPuzzleDeseado(@Header("Authorization") token: String?, @Path("id") idPuzzle: Long) : Call<Puzzle>

    @DELETE("puzzle/deseado/{id}")
    fun deletePuzzleDeseado(@Header("Authorization") token: String?, @Path("id") idPuzzle: Long) : Call<Any>

    @GET("puzzle/deseado")
    fun getListaPuzzlesDeseados(@Header("Authorization")token: String?): Call<List<Puzzle>>

}