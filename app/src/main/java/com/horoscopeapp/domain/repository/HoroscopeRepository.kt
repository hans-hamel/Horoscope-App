package com.horoscopeapp.domain.repository

import com.horoscopeapp.domain.model.PredictionModel

interface HoroscopeRepository {
    suspend fun getPrediction(sign: String) : PredictionModel?
}