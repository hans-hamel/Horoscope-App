package com.horoscopeapp.ui.detail

import com.horoscopeapp.domain.model.HoroscopeModel

sealed class HoroscopeDetailState {
    data object Loading: HoroscopeDetailState()
    data class Success(val prediction: String, val sign: String, val horoscopeModel: HoroscopeModel): HoroscopeDetailState()
    data class Failure(val error: String): HoroscopeDetailState()
}