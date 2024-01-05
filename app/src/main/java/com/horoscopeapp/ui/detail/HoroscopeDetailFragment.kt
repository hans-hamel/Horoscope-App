package com.horoscopeapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.horoscopeapp.R
import com.horoscopeapp.databinding.FragmentHoroscopeDetailBinding
import com.horoscopeapp.domain.model.HoroscopeModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailFragment : Fragment() {

    private val viewModel: HoroscopeDetailViewModel by viewModels()
    private var _binding: FragmentHoroscopeDetailBinding? = null
    private val binding get() = _binding!!
    private val args: HoroscopeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeDetailBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        val horoscopeModel = when (args.horoscope) {
            R.string.aquarius -> HoroscopeModel.Aquarius
            R.string.aries -> HoroscopeModel.Aries
            R.string.cancer -> HoroscopeModel.Cancer
            R.string.capricorn -> HoroscopeModel.Capricorn
            R.string.gemini -> HoroscopeModel.Gemini
            R.string.leo -> HoroscopeModel.Leo
            R.string.libra -> HoroscopeModel.Libra
            R.string.pisces -> HoroscopeModel.Pisces
            R.string.sagittarius -> HoroscopeModel.Sagittarius
            R.string.scorpio -> HoroscopeModel.Scorpio
            R.string.taurus -> HoroscopeModel.Taurus
            R.string.virgo -> HoroscopeModel.Virgo
            else -> throw (Error("Invalid sign")) // Improve
        }
        viewModel.getPrediction(horoscopeModel)
    }

    private fun initUI() {
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { horoscopeDetailState ->
                    when (horoscopeDetailState) {
                        is HoroscopeDetailState.Failure -> loadingState()
                        HoroscopeDetailState.Loading -> failureState()
                        is HoroscopeDetailState.Success -> successState(horoscopeDetailState)
                    }
                }
            }
        }
    }

    private fun failureState() {
        binding.pbLoading.isVisible = false
    }

    private fun loadingState() {
        binding.pbLoading.isVisible = true
    }

    private fun successState(horoscopeDetailState: HoroscopeDetailState.Success) {
        //binding.pbLoading.isVisible = false
        binding.tvHoroscopeDetailTitle.text = horoscopeDetailState.sign
        binding.tvHoroscopeDetailBody.text = horoscopeDetailState.prediction

        val image = when (horoscopeDetailState.horoscopeModel) {
            HoroscopeModel.Aries -> R.drawable.detail_aries
            HoroscopeModel.Taurus -> R.drawable.detail_taurus
            HoroscopeModel.Gemini -> R.drawable.detail_gemini
            HoroscopeModel.Cancer -> R.drawable.detail_cancer
            HoroscopeModel.Leo -> R.drawable.detail_leo
            HoroscopeModel.Virgo -> R.drawable.detail_virgo
            HoroscopeModel.Libra -> R.drawable.detail_libra
            HoroscopeModel.Scorpio -> R.drawable.detail_scorpio
            HoroscopeModel.Sagittarius -> R.drawable.detail_sagittarius
            HoroscopeModel.Capricorn -> R.drawable.detail_capricorn
            HoroscopeModel.Aquarius -> R.drawable.detail_aquarius
            HoroscopeModel.Pisces -> R.drawable.detail_pisces
        }

        binding.ivHoroscopeDetail.setImageResource(image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}