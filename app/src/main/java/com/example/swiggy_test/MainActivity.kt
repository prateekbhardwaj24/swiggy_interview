package com.example.swiggy_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.swiggy_test.ui.navgraph.PokemonNavGraph
import com.example.swiggy_test.ui.theme.Swiggy_testTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Swiggy_testTheme {
                PokemonNavGraph()
            }
        }
    }
}
@Composable
fun SwiggyTest(){

}


/*
image
https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/2.png

api for listings
https://pokeapi.co/api/v2/pokemon?limit=20&offset=1


api for detail
https://pokeapi.co/api/v2/pokemon/ivysaur
 */