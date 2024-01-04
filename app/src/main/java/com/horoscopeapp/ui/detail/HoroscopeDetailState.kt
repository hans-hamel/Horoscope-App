package com.horoscopeapp.ui.detail

sealed class HoroscopeDetailState {
    data object Loading: HoroscopeDetailState()
    data class Success(val data: String): HoroscopeDetailState()
    data class Failure(val error: String): HoroscopeDetailState()
}