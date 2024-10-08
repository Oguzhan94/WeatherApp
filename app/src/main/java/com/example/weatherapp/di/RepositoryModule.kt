package com.example.weatherapp.di

import com.example.weatherapp.data.repository.RestDataSourceImpl
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        restDataSourceImpl: RestDataSourceImpl
    ): WeatherRepository {
        return restDataSourceImpl
    }
}