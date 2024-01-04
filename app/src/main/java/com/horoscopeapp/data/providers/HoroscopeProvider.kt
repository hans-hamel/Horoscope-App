package com.horoscopeapp.data.providers

import com.horoscopeapp.domain.model.HoroscopeInfo
import javax.inject.Inject

class HoroscopeProvider @Inject constructor() {
    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf(
            HoroscopeInfo.Aries,
            HoroscopeInfo.Taurus,
            HoroscopeInfo.Gemini,
            HoroscopeInfo.Cancer,
            HoroscopeInfo.Leo,
            HoroscopeInfo.Virgo,
            HoroscopeInfo.Libra,
            HoroscopeInfo.Scorpio,
            HoroscopeInfo.Capricorn,
            HoroscopeInfo.Sagittarius,
            HoroscopeInfo.Aquarius,
            HoroscopeInfo.Pisces
        )
    }
}