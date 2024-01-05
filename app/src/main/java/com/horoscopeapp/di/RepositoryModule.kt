package com.horoscopeapp.di

import com.horoscopeapp.data.network.HoroscopeApiService
import com.horoscopeapp.data.network.repository.HoroscopeRepositoryImpl
import com.horoscopeapp.domain.repository.HoroscopeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(apiService: HoroscopeApiService): HoroscopeRepository {
        return HoroscopeRepositoryImpl(apiService)
    }
}