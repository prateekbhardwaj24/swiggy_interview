package com.example.swiggy_test.di

import com.example.swiggy_test.data.repository.PokemonRepositoryImpl
import com.example.swiggy_test.domain.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class NetworkModule {
//
//    @Singleton
//    @Binds
//    abstract fun bindPokemonRepository(pokemonRepositoryImpl: PokemonRepositoryImpl) : PokemonRepository
//}