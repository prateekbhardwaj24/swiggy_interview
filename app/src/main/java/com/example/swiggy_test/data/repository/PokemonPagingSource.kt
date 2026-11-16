package com.example.swiggy_test.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.swiggy_test.data.model.Pokemon
import com.example.swiggy_test.data.remote.PokemonApiService

class PokemonPagingSource(
    private val pokemonApiService: PokemonApiService
): PagingSource<Int, Pokemon>() {
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: 1
        return try {
            val response = pokemonApiService.getPokemons(
                limit = params.loadSize,
                page = page
            )
            val pokemons = response.results ?: emptyList()

            LoadResult.Page(
                data = pokemons,
                prevKey = if(page ==1) null else page-1,
                nextKey = if(pokemons.isEmpty()) null else page+1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}