package com.example.swiggy_test.ui.listScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.swiggy_test.data.model.Pokemon
import com.example.swiggy_test.domain.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class PokemonListViewmodel @Inject constructor(
    repository: PokemonRepository
): ViewModel() {
    val pokemons: Flow<PagingData<Pokemon>> = repository.getPokemonStream().cachedIn(viewModelScope)
}