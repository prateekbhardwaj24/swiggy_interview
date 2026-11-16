package com.example.swiggy_test.ui.listScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.swiggy_test.data.model.Pokemon
import com.example.swiggy_test.ui.common.ErrorMessage
import com.example.swiggy_test.ui.common.LoadingIndicator

@Composable
fun PokemonListScreen(
    onPokemonClick: (String) -> Unit,
    viewmodel: PokemonListViewmodel = hiltViewModel()
) {
    val pokemons = viewmodel.pokemons.collectAsLazyPagingItems()
    Scaffold() { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val refreshState = pokemons.loadState.refresh) {
                is LoadState.Loading -> {
                    LoadingIndicator()
                }

                is LoadState.Error -> {
                    ErrorMessage(
                        message = refreshState.error.localizedMessage ?: "Failed to load",
                        onRetry = { pokemons.retry() }
                    )
                }

                else -> {
                    LazyColumn(
                        contentPadding = PaddingValues(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(count = pokemons.itemCount) { index ->
                            val pokemon = pokemons[index]
                            if (pokemon != null) {
                                PokeMonCardItem(
                                    pokemon = pokemon,
                                    onClick = { onPokemonClick(pokemon.name) }
                                )
                            }

                        }
                    }
                }
            }

        }
    }
}

@Composable
fun PokeMonCardItem(
    pokemon: Pokemon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val image_prefix = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/"
    val image_url_check = pokemon.url.trimEnd('/').substringAfterLast('/')
    val finalImage = image_prefix+image_url_check+".png"
    Log.d("image list","${finalImage}")
    Card(
        onClick = { onClick() },
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = finalImage,
                contentDescription = pokemon.name,
                modifier = Modifier.size(width = 70.dp, height = 90.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = pokemon.name)
        }
    }
}