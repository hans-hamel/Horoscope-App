package com.horoscopeapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.horoscopeapp.domain.model.HoroscopeModel
import com.horoscopeapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(
    private val getPredictionUseCase: GetPredictionUseCase
) :
    ViewModel() {

    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state = _state

    private lateinit var horoscopeModel: HoroscopeModel

    fun getPrediction(sign: HoroscopeModel) {
        horoscopeModel = sign
        viewModelScope.launch {
            _state.value = HoroscopeDetailState.Loading

            val result = withContext(Dispatchers.IO) { getPredictionUseCase(sign.name) }

            _state.value = result?.let { predictionModel ->
                 HoroscopeDetailState.Success(predictionModel.horoscope, predictionModel.sign, horoscopeModel)
            } ?: HoroscopeDetailState.Failure("An error occurred.")
        }
    }
}