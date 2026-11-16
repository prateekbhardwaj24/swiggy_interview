package com.example.swiggy_test.data.remote

import com.example.swiggy_test.data.model.PokemonDetailsResponse
import com.example.swiggy_test.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET("api/v2/pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int = 20,
        @Query("offset") page: Int
    ) : PokemonResponse

    @GET("api/v2/pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name") name: String,
    ): PokemonDetailsResponse
}


//https://pokeapi.co/api/v2/pokemon?limit=20&offset=1