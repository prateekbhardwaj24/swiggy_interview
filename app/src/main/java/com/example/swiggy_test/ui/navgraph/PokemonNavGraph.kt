package com.example.swiggy_test.ui.navgraph

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swiggy_test.ui.detailScreen.PokemonDetailScreen
import com.example.swiggy_test.ui.listScreen.PokemonListScreen

sealed class Screen(val route: String){
    object PokemonList: Screen("pokemons")
    object PokemonDetail: Screen("pokemon_detail/{name}"){
        fun createRoute(pokemonName:String) = "pokemon_detail/$pokemonName"
    }
}

@Composable
fun PokemonNavGraph(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = Screen.PokemonList.route
    ) {
        composable (route = Screen.PokemonList.route){
            PokemonListScreen(
                onPokemonClick = { pokemonName->
                    Log.d("pokemonName","hey $pokemonName")
                    navController.navigate(Screen.PokemonDetail.createRoute(pokemonName))
                }
            )
        }
        composable (route = Screen.PokemonDetail.route){
            PokemonDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}