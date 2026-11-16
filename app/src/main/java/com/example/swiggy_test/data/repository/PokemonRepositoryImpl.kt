package com.example.swiggy_test.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.swiggy_test.data.model.Pokemon
import com.example.swiggy_test.data.model.PokemonDetailsResponse
import com.example.swiggy_test.data.remote.PokemonApiService
import com.example.swiggy_test.domain.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl(private val apiService: PokemonApiService) : PokemonRepository {
    override fun getPokemonStream(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = { PokemonPagingSource(pokemonApiService = apiService)}
        ).flow
    }

    override suspend fun getPokemonDetails(pokemonName: String): Result<PokemonDetailsResponse> {
        return try {
            val response = apiService.getPokemonDetails(pokemonName)
            Log.d("reponse_detail","${response}")
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}