package com.horoscopeapp.data.network.response

import com.horoscopeapp.motherobject.HoroscopeMotherObject
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import org.junit.Test

class PredictionResponseTest {

    @Test
    fun `given a PredictionResponse when toDomain then should return a correct PredictionModel`() {
        val predictionResponse = HoroscopeMotherObject.predictionResponse
        val predictionModel = predictionResponse.toDomain()

        predictionModel.sign shouldBe predictionResponse.sign
        predictionModel.horoscope shouldBe predictionResponse.horoscope
    }

    @Test
    fun `given a PredictionResponse when toDomain then return a different PredictionModel as another PredictionModel`() {
        val predictionResponse = HoroscopeMotherObject.predictionResponse
        val anotherPredictionResponse = predictionResponse.copy(
            date = "05/03/2023",
            horoscope = "Another horoscope",
            sign = "Libra",
        )
        val predictionModel = predictionResponse.toDomain()
        val anotherPredictionModel = anotherPredictionResponse.toDomain()

        predictionModel shouldNotBe anotherPredictionModel.sign
        predictionModel shouldNotBe anotherPredictionModel.horoscope

    }
}