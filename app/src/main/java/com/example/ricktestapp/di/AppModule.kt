package com.example.ricktestapp.di

import com.example.ricktestapp.repositories.LocationRepository
import com.example.ricktestapp.repositories.CharacterRepository
import com.example.ricktestapp.repositories.EpisodesRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideCharacterRepository() = CharacterRepository()

    @Provides
    fun provideLocationRepository() = LocationRepository()

    @Provides
    fun provideEpisodesRepository() = EpisodesRepository()

}