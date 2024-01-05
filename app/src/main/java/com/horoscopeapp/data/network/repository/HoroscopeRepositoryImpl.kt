package com.horoscopeapp.data.network.repository

import com.horoscopeapp.data.network.HoroscopeApiService
import com.horoscopeapp.domain.model.PredictionModel
import com.horoscopeapp.domain.repository.HoroscopeRepository
import javax.inject.Inject

class HoroscopeRepositoryImpl @Inject constructor(private val horoscopeApiService: HoroscopeApiService) :
    HoroscopeRepository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching { horoscopeApiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { /* Log error */ }
        return null
    }
}