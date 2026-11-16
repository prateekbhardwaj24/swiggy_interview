package com.example.swiggy_test.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class PokemonResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("prev") val prev: String,
    @SerializedName("results") val results: List<Pokemon>,
)

data class Pokemon(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)


// detail page

data class PokemonDetailsResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: Sprites,
)

data class Sprites(
    @SerializedName("other") val others: Home
)

data class Home(
    @SerializedName("home") val home: Image
)

data class Image(
    @SerializedName("front_default") val frontDefault: String?
)