package com.example.ricktestapp.di

import com.example.ricktestapp.repositories.LocationRepository
import com.example.ricktestapp.repositories.CharacterRepository
import com.example.ricktestapp.repositories.EpisodesRepository
import com.example.ricktestapp.viewmodels.CharactersViewModel
import com.example.ricktestapp.viewmodels.EpisodesViewModel
import com.example.ricktestapp.viewmodels.LocationViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(repository: CharacterRepository)

    fun inject(repository: LocationRepository)

    fun inject(repository: EpisodesRepository)

    fun inject(viewModel: CharactersViewModel)

    fun inject(viewModel: LocationViewModel)

    fun inject(viewModel: EpisodesViewModel)

}