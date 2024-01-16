package com.horoscopeapp.motherobject

import com.horoscopeapp.data.network.response.PredictionResponse
import com.horoscopeapp.domain.model.HoroscopeInfo

object HoroscopeMotherObject {
    val predictionResponse = PredictionResponse("05/10/2023", "Prediction text", "Aries")
    val horoscopeList = listOf(
        HoroscopeInfo.Aries,
        HoroscopeInfo.Taurus,
        HoroscopeInfo.Gemini,
        HoroscopeInfo.Cancer,
        HoroscopeInfo.Leo,
        HoroscopeInfo.Virgo,
        HoroscopeInfo.Libra,
        HoroscopeInfo.Scorpio,
        HoroscopeInfo.Sagittarius,
        HoroscopeInfo.Capricorn,
        HoroscopeInfo.Aquarius,
        HoroscopeInfo.Pisces
    )
}