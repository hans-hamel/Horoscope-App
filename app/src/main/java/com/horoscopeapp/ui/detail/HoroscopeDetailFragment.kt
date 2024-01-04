package com.horoscopeapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.horoscopeapp.databinding.FragmentHoroscopeDetailBinding
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
                        is HoroscopeDetailState.Success -> successState()
                    }
                }
            }
        }
    }

    private fun failureState() {
        binding.pbLoading.visibility = View.INVISIBLE
    }

    private fun loadingState() {
        binding.pbLoading.visibility = View.VISIBLE
    }

    private fun successState() {
        binding.pbLoading.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}