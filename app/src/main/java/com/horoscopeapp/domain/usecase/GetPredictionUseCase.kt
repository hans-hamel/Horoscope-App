package com.horoscopeapp.domain.usecase

import com.horoscopeapp.domain.repository.HoroscopeRepository
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repositoryImpl: HoroscopeRepository) {

    suspend operator fun invoke(sign: String) = repositoryImpl.getPrediction(sign)
}