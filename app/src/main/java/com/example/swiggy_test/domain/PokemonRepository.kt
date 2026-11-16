package com.example.swiggy_test.domain

import androidx.paging.PagingData
import com.example.swiggy_test.data.model.Pokemon
import com.example.swiggy_test.data.model.PokemonDetailsResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonStream() : Flow<PagingData<Pokemon>>
    suspend fun getPokemonDetails(pokemonName: String) : Result<PokemonDetailsResponse>
}