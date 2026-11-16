package com.example.swiggy_test.ui.detailScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.swiggy_test.data.model.PokemonDetailsResponse
import com.example.swiggy_test.ui.common.ErrorMessage
import com.example.swiggy_test.ui.common.LoadingIndicator


@Composable
fun PokemonDetailScreen(
    onBackClick: () -> Unit,
    viewmodel: PokemonDetailViewmodel = hiltViewModel()
){
    val uiState by viewmodel.uiState.collectAsState()

    Scaffold {paddignValue->
        Box(
            modifier = Modifier.padding(paddignValue)
        ){
            when(val state = uiState){
                is PokemonDetailUiState.Loading ->{
                    LoadingIndicator()
                }
                is PokemonDetailUiState.Error ->{
                    ErrorMessage(message = state.message,
                        onRetry = {viewmodel.loadDetails()})
                }
                is PokemonDetailUiState.Success ->{
                    PokemonDetailContent(pokemon = state.pokemon)
                }
            }
        }
    }
}

@Composable
fun PokemonDetailContent(pokemon: PokemonDetailsResponse, modifier:Modifier = Modifier) {
    val image =pokemon.sprites.others.home.frontDefault
    Log.d("image","${image}")
    Column(modifier = modifier
        .fillMaxSize()){
        AsyncImage(
            model = if(!(image.isNullOrEmpty())) image else "",
            contentDescription = pokemon.name
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = pokemon.name)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = pokemon.id)
    }
}
